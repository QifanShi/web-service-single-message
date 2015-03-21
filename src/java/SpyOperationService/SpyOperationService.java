package SpyOperationService;

import SpyOperationUtil.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * The class SpyOperationService provides web service includes adding a new spy,
 * delete an existing spy, returns spy list in simple String format or XML
 * string format.The communication between server and client implements with
 * single message.
 *
 * @author Qifan Shi
 * @version 1.0 Last Modified: 10/24/2014
 */
@WebService(serviceName = "SpyOperationService")
public class SpyOperationService {

    private SpyList spylist;

    public SpyOperationService() {
        spylist = SpyList.getInstance();
    }

    /**
     * Web service operation: spy service.
     *
     * @param XMLString an XML string from client contains operation
     * information.
     * @return the result of desired operation.
     */
    @WebMethod(operationName = "SpyOperation")
    public String SpyOperation(@WebParam(name = "XMLString") String XMLString) {

        String result;
        SpyMessage sm = new SpyMessage(XMLString);
        sm.parseXML();
        switch (sm.getOperation()) {
            case "add": {
                spylist.add(sm.getSpy());
                result = "Spy " + sm.getSpy().getName() + " was added in list.";
                break;
            }
            case "delete": {
                // check if the spy is in the list.
                if (spylist.get(sm.getSpy().getName()) == null) {
                    result = "Spy " + sm.getSpy().getName() + " was not in the list";
                } else {
                    spylist.delete(new Spy(sm.getSpy().getName()));
                    result = "Spy " + sm.getSpy().getName() + " was removed from the list";
                }
                break;
            }
            case "list": {
                result = spylist.toString();
                break;
            }
            case "listXML": {
                result = spylist.toXML();
                break;
            }
            default:
                result = "No such Operation: " + sm.getOperation();
        }
        return result;
    }

}
