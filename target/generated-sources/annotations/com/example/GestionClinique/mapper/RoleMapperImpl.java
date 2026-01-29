package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.RoleRequestDto;
import com.example.GestionClinique.dto.ResponseDto.RoleResponseDto;
import com.example.GestionClinique.model.entity.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T12:51:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleResponseDto toDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId( role.getId() );
        roleResponseDto.setRoleType( role.getRoleType() );

        return roleResponseDto;
    }

    @Override
    public Role toEntity(RoleRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );

        return role;
    }

    @Override
    public List<RoleResponseDto> toDtoList(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleResponseDto> list = new ArrayList<RoleResponseDto>( roles.size() );
        for ( Role role : roles ) {
            list.add( toDto( role ) );
        }

        return list;
    }
}
