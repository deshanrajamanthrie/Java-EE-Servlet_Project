package lk.ijse.gdse.pos.mapper;

import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ObjectMapper {
    ObjectMapper INSTANCE = Mappers.getMapper(ObjectMapper.class);

    @Mapping(target = "id", ignore = true)
        // If the entity has an ID field that shouldn't be mapped from DTO
    Customer dtoToEntity(CustomerDTO dto);
}
