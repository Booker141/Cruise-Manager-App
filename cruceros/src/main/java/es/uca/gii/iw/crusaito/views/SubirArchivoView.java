/*package es.uca.gii.iw.crusaito.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import es.uca.gii.iw.crusaito.common.Funciones;

@Route(value = "SubirArchivo", layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Admin")
public class SubirArchivoView extends VerticalLayout{

	private MultiFileMemoryBuffer buffer;
	private Upload upload = new Upload(buffer);
	private Div output;
	
	@Autowired
	public SubirArchivoView() {
		
		buffer = new MultiFileMemoryBuffer();
		upload = new Upload(buffer);
		output = new Div();
		
		upload.setMaxFiles(1);
		upload.setDropLabel(new Label("Solo válido formato .jpg"));
		upload.setAcceptedFileTypes(".jpg",".JPG");
		
		upload.addSucceededListener(event -> {
            try {
                byte[] buf = new byte[(int)event.getContentLength()];
                InputStream is = buffer.getInputStream(event.getFileName());
                is.read(buf);
                File targetFile = new File("src/main/resources/META-INF/resources/img/"+event.getFileName());
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(buf);
                outStream.flush();
                outStream.close();
                Component component = createComponent(event.getMIMEType(),
                        event.getFileName(),
                        buffer.getInputStream(event.getFileName()));
                showOutput(event.getFileName(), component, output);
                Funciones.notificacionAcierto("Archivo subido correctamente");
            } catch (IOException ex) {
            	Funciones.notificacionError("Error al subir archivo");
            }
        });
		
		upload.addFileRejectedListener(event -> {
		    Paragraph component = new Paragraph();
		    showOutput(event.getErrorMessage(), component, output);
		});
		
		add(upload,output);
	}
	
	private Component createComponent(String mimeType, String fileName,
            InputStream stream) {
        if (mimeType.startsWith("image")) {
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

  private void showOutput(String text, Component content,
            HasComponents outputContainer) {
        HtmlComponent p = new HtmlComponent(Tag.P);
        p.getElement().setText(text);        
        outputContainer.add(p);
        outputContainer.add(content);
    }
}*/
