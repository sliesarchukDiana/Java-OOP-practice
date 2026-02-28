boolean checkURLValidity(String stringURL) throws MalformedURLException {
    if (stringURL == null || stringURL.isEmpty()) {
        throw new MalformedURLException("URL is empty");
    }
    try {
        URL url = new URI(stringURL).toURL();
        return true;
    } catch (MalformedURLException | URISyntaxException e) {
        IO.println("Incorrect URL format: " + e.getMessage());
        return false;
    }
}

void filterInvalidURLs(List<String> urlList) {
    urlList.removeIf(url -> {
        try {
            return !checkURLValidity(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    });
}

void main() {
    ArrayList<String> urlList = new ArrayList<>(Arrays.asList(
            "https://github.com",
            "hop://github.com",
            "http://guthib.com",
            "https://java.com",
            "hippy://github.com",
            "hippo://java.com"
    ));
    IO.println("List before filtration:");
    for (String url : urlList) {
        IO.println(url);
    }
    filterInvalidURLs(urlList);
    IO.println("\n");
    IO.println("List after filtration:");
    for (String url : urlList) {
        IO.println(url);
    }
}