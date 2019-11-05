package es.uca.gii.iw.crusaito.spring;

import java.io.Serializable;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service
public class MessageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
        return "Button was clicked at " + LocalTime.now();
    }
}
