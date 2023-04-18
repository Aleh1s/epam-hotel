package ua.aleh1s.hotelepam.validator.impl;

import ua.aleh1s.hotelepam.model.dto.RequestDto;
import ua.aleh1s.hotelepam.validator.Validator;

public class RequestDtoValidator extends Validator<RequestDto> {

    @Override
    public void validate(RequestDto target) {
        validateRoomNumber(target.getRoomNumber());
        validatePeriod(target.getCheckIn(), target.getCheckOut());
    }

    private void validateRoomNumber(Integer roomNumber) {
        var fieldName = "roomNumber";

        if (isNull(roomNumber)) {
            rejectValue(fieldName, requiredValueMessage(fieldName));
        } else {
            if (roomNumber < 1)
                rejectValue(fieldName, "Room number should be greater than 0");
        }
    }
}
