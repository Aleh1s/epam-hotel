package ua.aleh1s.hotelepam.validator.impl;

import jakarta.servlet.http.Part;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.validator.Validator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class RoomDtoValidator extends Validator<RoomDto> {

    @Override
    public void validate(RoomDto target) {
        validatePositiveNumber("number", target.getNumber());
        validatePositiveNumber("beds", target.getBeds());
        validatePositiveNumber("guests", target.getGuests());
        validatePositiveNumber("area", target.getArea());
        validateTitle(target.getTitle());
        validateDescription(target.getDescription());
        validatePrice(target.getPrice());
        validateImage(target.getImage());
    }

    private void validatePositiveNumber(String fieldName, Integer number) {
        if (isNull(number))
            rejectValue(fieldName, requiredValueMessage(fieldName));
        else if (number <= 0)
            rejectValue(fieldName, "Number should be greater than 0");
    }

    private void validateTitle(String title) {
        String fieldName = "title";

        if (isNull(title))
            rejectValue(fieldName, requiredValueMessage(fieldName));
        else {
            if (isBlank(title))
                rejectValue(fieldName, emptyValueMessage(fieldName));

            if (title.length() < 10 || title.length() > 100)
                rejectValue(fieldName, "Field length should be between 10 and 100");
        }
    }

    private void validateDescription(String desc) {
        String fieldName = "description";

        if (isNull(desc))
            rejectValue(fieldName, requiredValueMessage(fieldName));
        else {
            if (isBlank(fieldName))
                rejectValue(fieldName, emptyValueMessage(fieldName));

            if (desc.length() < 40 || desc.length() > 800)
                rejectValue(fieldName, "Field length should be between 40 and 800");
        }
    }

    private void validatePrice(BigDecimal price) {
        String fieldName = "price";

        if (isNull(price))
            rejectValue(fieldName, requiredValueMessage(fieldName));
        else if (price.compareTo(BigDecimal.ZERO) < 0) {
            rejectValue(fieldName, "Price should be greater than 0");
        }
    }

    private void validateImage(Part image) {
        String fieldName = "image";

        if (isNull(image))
            rejectValue(fieldName, requiredValueMessage(fieldName));
    }
}
