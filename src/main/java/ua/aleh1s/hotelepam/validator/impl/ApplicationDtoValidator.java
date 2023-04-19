package ua.aleh1s.hotelepam.validator.impl;

import ua.aleh1s.hotelepam.model.dto.ApplicationDto;
import ua.aleh1s.hotelepam.validator.Validator;

public class ApplicationDtoValidator extends Validator<ApplicationDto> {

    @Override
    public void validate(ApplicationDto target) {
        validateGuests(target.getGuests());
        validatePeriod(target.getCheckIn(), target.getCheckOut());
    }

    private void validateGuests(Integer guests) {
        var fieldName = "guests";

        if (isNull(guests)) {
            rejectValue(fieldName, requiredValueMessage(fieldName));
        } else {
            if (guests < 1 || guests > 10)
                rejectValue(fieldName, "Guests should be between 1 and 10");
        }
    }
}
