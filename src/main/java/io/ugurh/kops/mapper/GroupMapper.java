package io.ugurh.kops.mapper;

import io.ugurh.kops.dto.GroupDto;
import io.ugurh.kops.entity.Group;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroupMapper {
    Group groupDtoToGroup(GroupDto groupDto);

    GroupDto groupToGroupDto(Group group);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGroupFromGroupDto(GroupDto groupDto, @MappingTarget Group group);
}
