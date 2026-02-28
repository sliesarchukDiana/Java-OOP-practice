import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

boolean checkURLValidity (String stringURL){
    if (stringURL == null || stringURL.isEmpty()){
        return false;
    }
    try {
        URL url = new URI(stringURL).toURL();
        return true;
    }
    catch (MalformedURLException | URISyntaxException e) {
        System.out.println("Incorrect URL format: " + e.getMessage());
        return false;
    }
}

void main() {
    System.out.println(checkURLValidity("https://github.com"));
    System.out.println(checkURLValidity("hop://github.com"));
    System.out.println(checkURLValidity("http://guthib.com"));
}