package br.com.instacoffe.app.utils.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class HttpUtil {

    private HttpUtil(){};

    public static URI makeUriFromCurrentRequest(Object object){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object).toUri();
    }

    public static <T> ResponseEntity<T> created(T data, Object id){
        URI uri = makeUriFromCurrentRequest(id);
        return ResponseEntity.created(uri).body(data);
    }

    public static <T> ResponseEntity<T> ok(T data){
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
