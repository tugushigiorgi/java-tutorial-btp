package com.example.javatutorial;
import static org.springframework.util.ObjectUtils.isEmpty;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class ControllerResponse {

  public static  <T> ResponseEntity<List<T>> handleList(List<T> data) {
    return data.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(data);
  }
  public static <T> ResponseEntity<T> handleItemNotFoundOrOk(T data) {
    return isEmpty(data)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(data);
  }
}
