package pillihuaman.com.pe.basebd.parameter.dao;

import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.basebd.parameter.Parameter;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;

import java.util.List;

public interface ParameterRepository extends BaseMongoRepository<Parameter> {


    Parameter  saveParemeter(Parameter request, MyJsonWebToken tok);
    List<Parameter> getParameterByIdCode(Parameter request);
    List<Parameter> getParameterByName(String request);
}
