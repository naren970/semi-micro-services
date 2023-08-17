package com.gotracrat.managecompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.resource.resource.TemplateWithOutXml;
import com.gotracrat.managecompany.entity.Template;

/*
 * Jpa repository for Template. 
 * @author manikanta
 * since
 */

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer>, JpaSpecificationExecutor<Template> {

	List<Template> findBycompanyID(Integer companyId);

	Template findBytemplateID(Integer templateId);

	@Query(value="select new com.gotracrat.managecompany.resource.resource.TemplateWithOutXml"
			+ "(t.templateID,t.name,t.companyID) from Template t where t.companyID=:companyID")
	public List<TemplateWithOutXml> getAllTemplatesWithOutXMl(Integer companyID);
}
