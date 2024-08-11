package inatools.backend.converter;

import inatools.backend.domain.CommonCondition;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class CommonConditionConverter implements AttributeConverter<List<CommonCondition>, String> {

    @Override
    public String convertToDatabaseColumn(List<CommonCondition> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<CommonCondition> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(dbData.split(","))
                .map(CommonCondition::valueOf)
                .collect(Collectors.toList());
    }

}
