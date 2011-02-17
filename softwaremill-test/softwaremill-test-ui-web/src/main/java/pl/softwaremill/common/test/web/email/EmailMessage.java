package pl.softwaremill.common.test.web.email;

import com.dumbster.smtp.SmtpMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Class wraps SmtpMessage from mock email server and represents an email message
 * with methods to extract certain parts of it.
 *
 * @author maciek
 */
public class EmailMessage {

    private SmtpMessage email;

    public EmailMessage(SmtpMessage email) {
        this.email = email;
    }

    public String getSubject(){
        return email.getHeaderValue("Subject");
    }

    public String getMessage(){
        return email.getBody();
    }

    public String getToHeader(){
        return email.getHeaderValue("To");
    }

    public String getFromHeader(){
        return email.getHeaderValue("From");
    }

    public String getHeaderValue(EmailHeader header){
        return email.getHeaderValue(header.getValue());
    }

    public String[] getHeaderValues(EmailHeader header){
        return email.getHeaderValues(header.getValue());
    }

    /**
     * Finds all links in email message.
     * Does not validate proper url format - finds any http[s]://text-with-no-space
     *
     * @return List of Strings with found links, or empty list if none found
     */
    public List<String> getLinksInMessage() {
        List<String> links = new ArrayList<String>();
        String msg = getMessage();
        Pattern p = Pattern.compile("\\bhttps?[^\\s]*\\b");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            String link = m.group();
            links.add(link);
        }

        return links;
    }

    /**
     * Finds first link in email message with given part
     *
     * @param urlPart part of url we are looking for, e.g. not changing action part
     * @return Url String with matching link, or null if none found
     */
    public String getLinkLike(String urlPart) {
        for (String link : getLinksInMessage()) {
            if (link.contains(urlPart)) {
                return link;
            }
        }

        return null;
    }

    /**
     * Finds links in email message with given part
     *
     * @param urlPart part of url we are looking for, e.g. not changing action part
     * @return List of Strings with matching links, or empty list if none found
     */
    public List<String> getLinksLike(String urlPart) {
        List<String> links = new ArrayList<String>();
        for (String link : getLinksInMessage()) {
            if (link.contains(urlPart)) {
                links.add(link);
            }
        }

        return links;
    }
}