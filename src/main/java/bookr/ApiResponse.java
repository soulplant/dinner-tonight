package bookr;

public interface ApiResponse {
  static ApiResponse create(String data) {
    return new ApiResponse() {
      @Override
      public String getData() {
        return data;
      }
    };
  }

  String getData();
}
