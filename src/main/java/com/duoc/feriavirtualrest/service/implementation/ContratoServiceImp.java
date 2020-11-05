package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Contrato;
import com.duoc.feriavirtualrest.model.ContratoModel;
import com.duoc.feriavirtualrest.repository.ContratoRepository;
import com.duoc.feriavirtualrest.service.ContratoService;
import com.duoc.feriavirtualrest.service.ProcedureService;
import oracle.sql.DATE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("contratoService")
public class ContratoServiceImp implements ContratoService {

    Logger log = LoggerFactory.getLogger(ContratoServiceImp.class);

    @Autowired
    private ProcedureService procedureService;

    @Autowired
    @Qualifier("contratoRepository")
    private ContratoRepository contratoRepository;

    @Override
    public Object SP_CONTRATO_CREAR(Contrato contrato) {
        try{
            return contratoRepository.SP_CONTRATO_CREAR(
                    contrato.getFechainicio(),
                    contrato.getFechatermino(),
                    contrato.getFechacreacion()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo contrato", e);
            return new Object();
        }
    }

    @Override
    public List<Contrato> SP_CONTRATO_CONSULTAR(Contrato contrato) throws ClassNotFoundException {
        return (List<Contrato>)(procedureService.ejecutarSP(SPConstant.SP_CONTRATO_CONSULTAR, Contrato.class, contrato.generarDataIN()));
    }
}
