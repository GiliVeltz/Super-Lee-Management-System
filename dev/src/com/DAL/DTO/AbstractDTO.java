package com.DAL.DTO;

import com.DAL.DAO.AbstractMapper;

public class AbstractDTO {
    AbstractMapper abstractMapper;
    AbstractDTO(AbstractMapper abstractMapper){
        this.abstractMapper = abstractMapper;
    }
}
