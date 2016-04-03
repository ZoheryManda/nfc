/**
 *
 */
package com.mbds.nfc.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mbds.nfc.entities.Contact;

/**
 * @author katsi02
 *
 */
@XmlRootElement(name = "Response")
public class ContactsResponse extends BaseResponse<List<Contact>> {

    public ContactsResponse() {
        setData(new ArrayList<Contact>());
    }

    public ContactsResponse(List<Contact> contacts) {
        setData(contacts);
    }

}
