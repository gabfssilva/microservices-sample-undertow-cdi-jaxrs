package com.thedevpiece.mss.api;

import java.util.List;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Envelop<T> {
    private String uri;
    private T item;
    private List<T> items;
    private ErrorMessage error;

    public Envelop() {
    }

    private Envelop(Builder builder) {
        setUri(builder.uri);
        setItem((T) builder.item);
        setItems(builder.items);
        setError(builder.error);
    }

    public static Builder newEnvelop() {
        return new Builder();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Envelop{" +
                "uri='" + uri + '\'' +
                ", item=" + item +
                ", items=" + items +
                ", error=" + error +
                '}';
    }

    public static class ErrorMessage {
        private String message;

        public ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorMessage{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }

    public static final class Builder<T> {
        private String uri;
        private T item;
        private List<T> items;
        private ErrorMessage error;

        private Builder() {
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder item(T item) {
            this.item = item;
            return this;
        }

        public Builder items(List<T> items) {
            this.items = items;
            return this;
        }

        public Builder error(ErrorMessage error) {
            this.error = error;
            return this;
        }

        public Envelop build() {
            return new Envelop(this);
        }
    }
}
