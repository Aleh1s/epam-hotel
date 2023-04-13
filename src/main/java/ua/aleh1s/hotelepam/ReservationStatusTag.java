package ua.aleh1s.hotelepam;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReservationStatusTag extends TagSupport {

    private ReservationStatus status;

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();

        ResourceBundle bundle;
        if (Objects.nonNull(session))
            bundle = ResourceBundle.getBundle("locale", new Locale((String) session.getAttribute("lang")));
        else
            bundle = ResourceBundle.getBundle("locale", new Locale(""));

        JspWriter out = pageContext.getOut();
        try {
            String formattedPTag = "<p style=\"%s; margin: 0;\"><strong>%s</strong></p>";
            if (status.equals(ReservationStatus.PENDING_CONFIRMATION))
                out.println(String.format(formattedPTag, "color: #666666", bundle.getString("needs.confirmation")));
            else if (status.equals(ReservationStatus.PENDING_PAYMENT))
                out.println(String.format(formattedPTag, "color: #3c85ec", bundle.getString("needs.payment")));
            else if (status.equals(ReservationStatus.PAYED))
                out.println(String.format(formattedPTag, "color: green", bundle.getString("payed")));
            else if (status.equals(ReservationStatus.CANCELED))
                out.println(String.format(formattedPTag, "color: red", bundle.getString("canceled")));
            else
                throw new IllegalArgumentException("Unknown reservation status!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
