package com.hypesofts.homember.technical.exception;

import lombok.Getter;

@Getter
public class OwlException extends RuntimeException {

     private final OwlError error;

     public static OwlException of(OwlError error) {
          return new OwlException(error);
     }

     public OwlException(OwlError error) {
          super(error.getValue());
          this.error = error;
     }

     public String getReadableErrors() {
          return error.getValue();
     }
}
