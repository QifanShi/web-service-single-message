package SpyOperationUtil;

/**
 * The class Spy wraps a spy information in an object.The information includes a
 * spy name, spy title, a recent location of spy and individual password.Entire
 * spy information can be both retrieved in a simple string or an XML string.
 *
 * @author Michael J. McCarthy, Qifan Shi
 * @version 1.0 Last Modified: 10/24/2014
 */
public class Spy {

    // instance data for spies
    private String name;
    private String title;
    private String location;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Spy(String name, String title, String location, String password) {
        this.name = name;
        this.title = title;
        this.location = location;
        this.password = password;
    }

    public Spy(String name, String title, String location) {
        this.name = name;
        this.title = title;
        this.location = location;
        this.password = "";
    }

    public Spy(String name) {
        this.name = name;
        this.title = "";
        this.location = "";
        this.password = "";
    }

    public Spy() {
        this.name = "";
        this.title = "";
        this.location = "";
        this.password = "";

    }

    public String toXML() {
        StringBuffer xml = new StringBuffer();

        xml.append("<spy>");
        xml.append("<name>" + name + "</name>");
        xml.append("<spyTitle>" + title + "</spyTitle>");
        xml.append("<location>" + location + "</location>");
        xml.append("<password>" + password + "</password>");
        xml.append("</spy>");
        return xml.toString();

    }

}
