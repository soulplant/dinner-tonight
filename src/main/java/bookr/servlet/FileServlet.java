package bookr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileServlet extends HttpServlet {
  private final String servingPath;
  private final File staticAssetDir;
  private Map<String, String> mimeTypes;

  /**
   * Requests received by this servlet have the {@param servingPath} stripped from the URL, and then resolve to a file
   * located in {@param staticAssetDir}.
   *
   * @param servingPath    String that assets will be served under.
   * @param staticAssetDir Directory to resolve files from.
   */
  public FileServlet(String servingPath, File staticAssetDir) {
    this.servingPath = servingPath;
    this.staticAssetDir = staticAssetDir;
    this.mimeTypes = new HashMap<>();
  }

  public void addMimeTypes(Map<String, String> mimeTypes) {
    this.mimeTypes.putAll(mimeTypes);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String uri = req.getRequestURI();
    System.out.println("uri = " + uri);
    if (uri.isEmpty() || uri.equals("/")) {
      serveFile(resp, localFile("index.html"));
      return;
    }
    if (!uri.startsWith(servingPath)) {
      throw new IllegalArgumentException("Can't handle path '" + uri + "'");
    }
    File file = new File(staticAssetDir, uri.substring(servingPath.length()));
    serveFile(resp, file);
  }

  private void serveFile(HttpServletResponse resp, File file) throws IOException {
    if (!file.canRead()) {
      resp.setStatus(404);
      resp.getWriter().println("<h1>404 file not found</h1>");
      return;
    }
    resp.setContentLength((int) file.length());
    String mimeType = lookupMimeType(file);
    if (mimeType != null) {
      resp.setContentType(mimeType);
    }
    Files.copy(file.toPath(), resp.getOutputStream());
    resp.getOutputStream().close();
  }

  private String lookupMimeType(File file) {
    String[] segments = file.getPath().split("\\.");
    String extension = segments[segments.length - 1];
    return mimeTypes.get(extension);
  }

  private File localFile(String path) {
    return new File(staticAssetDir, path);
  }
}
