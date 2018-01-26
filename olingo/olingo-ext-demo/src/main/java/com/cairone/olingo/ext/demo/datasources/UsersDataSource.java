package com.cairone.olingo.ext.demo.datasources;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;
import org.apache.olingo.server.api.uri.queryoption.ExpandOption;
import org.apache.olingo.server.api.uri.queryoption.FilterOption;
import org.apache.olingo.server.api.uri.queryoption.OrderByOption;
import org.apache.olingo.server.api.uri.queryoption.SelectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cairone.olingo.ext.demo.dtos.UserFrmDto;
import com.cairone.olingo.ext.demo.dtos.validators.UserFrmDtoValidator;
import com.cairone.olingo.ext.demo.edm.resources.UserEdm;
import com.cairone.olingo.ext.demo.entities.UserEntity;
import com.cairone.olingo.ext.demo.exceptions.ODataBadRequestException;
import com.cairone.olingo.ext.demo.services.UserService;
import com.cairone.olingo.ext.demo.utils.OdataExceptionParser;
import com.cairone.olingo.ext.demo.utils.ValidatorUtil;
import com.cairone.olingo.ext.jpa.query.JPQLQuery;
import com.cairone.olingo.ext.jpa.query.JPQLQueryBuilder;

@Component
public class UsersDataSource extends AbstractDataSource {

	private static final String ENTITY_SET_NAME = "Users";

	@Autowired private UserService userService = null;
	@Autowired private UserFrmDtoValidator userFrmDtoValidator = null;
	
	@Override
	public String isSuitableFor() {
		return ENTITY_SET_NAME;
	}

	@Override
	public Object create(Object entity) throws ODataApplicationException {

		if(entity instanceof UserEdm) {
			
			UserEdm user = (UserEdm) entity;
			UserFrmDto userFrmDto = new UserFrmDto(user);
			
			try
			{
				ValidatorUtil.validate(userFrmDtoValidator, messageSource, userFrmDto);			
				UserEntity userEntity = userService.save(userFrmDto);
				
				return new UserEdm(userEntity);
				
			} catch (Exception e) {
				throw OdataExceptionParser.parse(e);
			}
		}
		
		throw new ODataBadRequestException("REQUEST DATA DOES NOT MATCH USER ENTITY");
	}

	@Override
	public Object update(Map<String, UriParameter> keyPredicateMap, Object entity, List<String> propertiesInJSON, boolean isPut) throws ODataApplicationException {

		if(entity instanceof UserEdm) {

			Integer userID = Integer.valueOf( keyPredicateMap.get("Id").getText() );
			
			UserEdm user = (UserEdm) entity;
			UserFrmDto userFrmDto = new UserFrmDto(user);
			
			userFrmDto.setId(userID);
			
			try
			{
				UserEntity userEntity = userService.findOne(userID);

    			if(!isPut) {
    				if(userFrmDto.getUsername() == null && !propertiesInJSON.contains("Username")) userFrmDto.setUsername(userEntity.getName());
    				if(userFrmDto.getPassword() == null && !propertiesInJSON.contains("Password")) userFrmDto.setPassword(userEntity.getPassword());
    			}
				
				ValidatorUtil.validate(userFrmDtoValidator, messageSource, userFrmDto);			
				userEntity = userService.save(userFrmDto);
				
				return new UserEdm(userEntity);
				
			} catch (Exception e) {
				throw OdataExceptionParser.parse(e);
			}
		}
		
		throw new ODataBadRequestException("REQUEST DATA DOES NOT MATCH PERSON ENTITY");
	}

	@Override
	public Object delete(Map<String, UriParameter> keyPredicateMap) throws ODataApplicationException {

		Integer userID = Integer.valueOf( keyPredicateMap.get("Id").getText() );

		try {
			UserEntity userEntity = userService.findOne(userID);
			userService.delete(userEntity);
		} catch (Exception e) {
			throw OdataExceptionParser.parse(e);
		}

		return null;
	}

	@Override
	public Object readFromKey(Map<String, UriParameter> keyPredicateMap, ExpandOption expandOption, SelectOption selectOption) throws ODataApplicationException {

		Integer userID = Integer.valueOf( keyPredicateMap.get("Id").getText() );
		
		try {
			UserEntity userEntity = userService.findOne(userID);
			UserEdm userEdm = new UserEdm(userEntity);
			
			return userEdm;
		} catch (Exception e) {
			throw OdataExceptionParser.parse(e);
		}
	}

	@Override
	public Iterable<?> readAll(ExpandOption expandOption, FilterOption filterOption, OrderByOption orderByOption) throws ODataApplicationException {

		JPQLQuery query = new JPQLQueryBuilder()
			.setDistinct(false)
			.setClazz(UserEdm.class)
			.setExpandOption(expandOption)
			.setFilterOption(filterOption)
			.setOrderByOption(orderByOption)
			.build();
	
		LOG.debug("EXECUTING QUERY: {}", query);
		List<UserEntity> userEntities = JPQLQuery.execute(entityManager, query);
		List<UserEdm> userEdms = userEntities.stream()
			.map(entity -> { 
				UserEdm userEdm = new UserEdm(entity);
				return userEdm;
			})
			.collect(Collectors.toList());
		
		return userEdms;
	}
}
