public class Store {
    String name;
    String url;
    String logoUrl;
    String country;
    boolean active=true;
    boolean subscriber=false;

    public Store(String name, String url, String logoUrl, String country) {
        this.name = name;
        this.url = url;
        this.logoUrl = logoUrl;
        this.country = country;
    }
}
