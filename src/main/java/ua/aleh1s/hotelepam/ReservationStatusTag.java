package ua.aleh1s.hotelepam;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.io.IOException;

public class ReservationStatusTag extends TagSupport {

    private ReservationStatus status;

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if (status.equals(ReservationStatus.PENDING_CONFIRMATION))
                out.println("<p style=\"color: #666666\">Needs confirmation</p>");
            else if (status.equals(ReservationStatus.PENDING_PAYMENT))
                out.println("<p style=\"color: #3c85ec\">Needs payment</p>");
            else if (status.equals(ReservationStatus.PAYED))
                out.println("<p style=\"color: green\">Payed</p>");
            else if (status.equals(ReservationStatus.CANCELED))
                out.println("<p style=\"color: red\">Canceled</p>");
            else
                throw new IllegalArgumentException("Unknown reservation status!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
