package es.uca.gii.iw.crusaito.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route(value = "SubirArchivo", layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Admin")
public class SubirArchivoView extends VerticalLayout{

	private MemoryBuffer buffer;
	private Upload upload = new Upload(buffer);
	private Div output;
	@Autowired
	public SubirArchivoView() {
		
		buffer = new MemoryBuffer();
		upload = new Upload(buffer);
		output = new Div();
		
		upload.setMaxFiles(1);
		upload.setDropLabel(new Label("Solo válido formato .jpg"));
		upload.setAcceptedFileTypes(".jpg");

		upload.addSucceededListener(event -> {
		    Component component = createComponent(event.getMIMEType(),
		            event.getFileName(), buffer.getInputStream());
		    showOutput(event.getFileName(), component, output);
		});
		
		upload.addFileRejectedListener(event -> {
		    Paragraph component = new Paragraph();
		    showOutput(event.getErrorMessage(), component, output);
		});
		
		add(upload,output);
	}
	
	private Component createComponent(String mimeType, String fileName,
            InputStream stream) {
        if (mimeType.startsWith("text")) {
          return createTextComponent(stream);
        } else if (mimeType.startsWith("image")) {
            Image image = new Image();
            try {

                byte[] bytes = IOUtils.toByteArray(stream);
                image.getElement().setAttribute("src", new StreamResource(
                        fileName, () -> new ByteArrayInputStream(bytes)));
                try (ImageInputStream in = ImageIO.createImageInputStream(
                        new ByteArrayInputStream(bytes))) {
                    final Iterator<ImageReader> readers = ImageIO
                            .getImageReaders(in);
                    if (readers.hasNext()) {
                        ImageReader reader = readers.next();
                        try {
                            reader.setInput(in);
                            image.setWidth(reader.getWidth(0) + "px");
                            image.setHeight(reader.getHeight(0) + "px");
                        } finally {
                            reader.dispose();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return image;
        }
        Div content = new Div();
        String text = String.format("Mime type: '%s'\nSHA-256 hash: '%s'",
                mimeType, MessageDigestUtil.sha256(stream.toString()));
        content.setText(text);
        return content;

    }

  private Component createTextComponent(InputStream stream) {
    String text;
    try {
        text = IOUtils.toString(stream, StandardCharsets.UTF_8);
    } catch (IOException e) {
        text = "exception reading stream";
    }
    return new Text(text);
  }

  private void showOutput(String text, Component content,
            HasComponents outputContainer) {
        HtmlComponent p = new HtmlComponent(Tag.P);
        p.getElement().setText(text);
        outputContainer.add(p);
        outputContainer.add(content);
    }
}
