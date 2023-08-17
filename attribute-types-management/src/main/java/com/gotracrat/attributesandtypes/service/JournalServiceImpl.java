/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.attributesandtypes.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.gotracrat.attributesandtypes.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.attributesandtypes.controller.resource.AttachmentDTO;
import com.gotracrat.attributesandtypes.controller.resource.AttributeChangeLog;
import com.gotracrat.attributesandtypes.controller.resource.ItemChangeLog;
import com.gotracrat.attributesandtypes.entity.Journal;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.AttachmentDAO;
import com.gotracrat.attributesandtypes.repository.AttachmentRepository;
import com.gotracrat.attributesandtypes.repository.JournalDAO;
import com.gotracrat.attributesandtypes.repository.JournalRepository;
import com.gotracrat.attributesandtypes.utils.EntityTypeIDEnum;
import com.gotracrat.attributesandtypes.utils.GoTracratConstants;
import com.gotracrat.attributesandtypes.utils.GotracratUtility;
import com.gotracrat.attributesandtypes.utils.ModulesEnum;

/**
 * Service implementation for Journal.
 * 
 * @author prabhakar
 */
@Service
public class JournalServiceImpl implements JournalService {

	@Autowired
	private JournalRepository journalRepository;
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private JournalDAO journalDAOImpl;
	
	@Autowired
	private AttachmentDAO attachmentDAOImpl;

	/*
	 * @Override public Page<Journal> search(Pageable pageable, JournalCriteria
	 * criteria) { return journalRepository.findAll(new
	 * JournalSpecification(criteria), pageable); }
	 */

	@Override
	public List<Journal> getAllNotes(int journalTypeId, int companyID, int entitytypeid, int entityid) {
		List<Journal> journalList = journalRepository.findByJournaltypeidAndCompanyidAndEntitytypeidAndEntityidOrderByEffectiveonDesc(journalTypeId, companyID,
				entitytypeid, entityid);
List<AttachmentDTO> attachmentList=attachmentDAOImpl.getAllAttachmentsForNotes(entityid,companyID,journalTypeId,entitytypeid);
		
		journalList.forEach(journal -> {
			List<AttachmentDTO> attachmentListTemp=new ArrayList<>();
			attachmentList.forEach(attachment -> {
				if(journal.getJournalid().equals(attachment.getEntityId()))
						{
					
					attachmentListTemp.add(attachment);
						}
				journal.setAttachmentList(attachmentListTemp);
							
			});
		});
		
		return journalList;
	}
	
	
	/*
	 * public List<Journal> getAllNotes(int companyID, int entitytypeid, int
	 * entityid) { List<Journal> journalList =
	 * journalRepository.findByCompanyidAndEntitytypeidAndEntityid(companyID,
	 * entitytypeid, entityid); //List<Journal> journalListWithAttachments = new
	 * ArrayList<>(); List<AttachmentDTO>
	 * attachmentList=attachmentDAOImpl.getAllAttachments(entityid);
	 * 
	 * journalList.forEach(journal -> { List<AttachmentDTO> attachmentListTemp=new
	 * ArrayList<>(); attachmentList.forEach(attachment -> {
	 * if(journal.getJournalid().equals(attachment.getEntityId())) {
	 * 
	 * attachmentListTemp.add(attachment); }
	 * journal.setAttachmentList(attachmentListTemp);
	 * //journalListWithAttachments.add(journal); }); });
	 */
	@Override
	public Journal get(Integer journalid) {
		Optional<Journal> journalOptional = journalRepository.findById(journalid);
		Journal journal = null;
		if(journalOptional.isPresent()){
			journal = journalOptional.get();
		}
		if(journal.getEntitytypeid()==2)
		{
		String xmlString = journal.getEntityxml();
		try {
			if (xmlString != null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(ItemChangeLog.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				ItemChangeLog itemChangeLog = (ItemChangeLog) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

				// System.out.println(itemChangeLog);
				journal.setItemChangeLog(itemChangeLog);
				String changeLogStr = buildString(itemChangeLog);
				journal.setChangeLogString(changeLogStr);
			}
			else
			{
				journal.setChangeLogString("None");
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		}
		else
		{
			journal.setChangeLogString("None");
		}
		return journal;
	}


	@Override
	public Journal create(Journal journal,UserLog userLog) {
		return journalRepository.save(journal);
	}

	@Override
	public Boolean save(Journal journal,UserLog userLog) {
		final Integer pk = journal.getJournalid();
		if (journalRepository.findById(pk).isPresent()) {
			journalRepository.save(journal);
			return true;
		}
		userLog.setCanNotInsert(true);
		return false;
	}

	@Override
	public Boolean exist(Journal journal) {
		return journalRepository.existsById(journal.getJournalid());
	}

	@Override
	public List<Journal> getAllNotes(int companyID, int entitytypeid, int entityid) {
		List<Journal> journalList = journalRepository.findByCompanyidAndEntitytypeidAndEntityidAndJournaltypeidOrderByEffectiveonDesc(companyID, entitytypeid, entityid, 3);
		List<AttachmentDTO> attachmentList=attachmentDAOImpl.getAllAttachments(entityid);
		
		journalList.forEach(journal -> {
			List<AttachmentDTO> attachmentListTemp=new ArrayList<>();
			attachmentList.forEach(attachment -> {
				if(journal.getJournalid().equals(attachment.getEntityId()))
						{
					
					attachmentListTemp.add(attachment);
						}
				journal.setAttachmentList(attachmentListTemp);
			});
		});
		
		
		return journalList;
	}

	private String buildString(ItemChangeLog itemChangeLog) {
		StringBuffer sb = new StringBuffer();
		String s;
		
		if(itemChangeLog.getLocation_Name()!=null&&itemChangeLog.getPrevious_LocationName()!=null)
		{
			
		if (!itemChangeLog.getLocation_Name().equalsIgnoreCase(itemChangeLog.getPrevious_LocationName())) {
			s = "Location....... was: " + itemChangeLog.getPrevious_LocationName() + ".......is: " + itemChangeLog.getLocation_Name()+"  ";
			sb.append(s);
		}
		}else
		{
			if(itemChangeLog.getLocationId()!=null &&itemChangeLog.getPrevious_LocationId()!=null)
			{
				
			if (!itemChangeLog.getLocationId().equals(itemChangeLog.getPrevious_LocationId())) {
				ItemChangeLog itemChangeLogTemp=journalDAOImpl.getLocationNames(itemChangeLog.getLocationId(),itemChangeLog.getPrevious_LocationId());
				s = "Location....... was: " + itemChangeLogTemp.getPrevious_LocationName() + ".......is: " + itemChangeLogTemp.getLocation_Name()+"  ";
				sb.append(s);
			}
			}	
		}
		
		if(itemChangeLog.getStatus_Name()!=null&&itemChangeLog.getPreviousStatus_Name()!=null)
		{
			
		if (!itemChangeLog.getStatus_Name().equalsIgnoreCase(itemChangeLog.getPreviousStatus_Name())) {
			s = "Status....... was: " + itemChangeLog.getPreviousStatus_Name() + ".......is: " + itemChangeLog.getStatus_Name()+"  ";
			sb.append(s);
		}
		}
		else
		{
			if(itemChangeLog.getStatusId()!=null &&itemChangeLog.getPrevious_StatusId()!=null)
			{
				
			if (!itemChangeLog.getStatusId().equals(itemChangeLog.getPrevious_StatusId())) {
				ItemChangeLog itemChangeLogTemp=journalDAOImpl.getStatusNames( itemChangeLog.getStatusId(),itemChangeLog.getPrevious_StatusId());
				s = "Status....... was: " + itemChangeLogTemp.getPreviousStatus_Name() + ".......is: " + itemChangeLogTemp.getStatus_Name()+"  ";
			//	s = "StatusId....... was: " + itemChangeLog.getPrevious_StatusId() + ".......is: " + itemChangeLog.getStatusId()+"  ";
				sb.append(s);
			}
			}
		}
		if(itemChangeLog.getTag()!=null&&itemChangeLog.getPrevious_Tag()!=null)
		{
			
		if (!itemChangeLog.getTag().equalsIgnoreCase(itemChangeLog.getPrevious_Tag())) {
			s = "Tag....... was: " + itemChangeLog.getPrevious_Tag() + ".......is: " + itemChangeLog.getTag()+"  ";
			sb.append(s);
		}
		}
		if(itemChangeLog.getName()!=null &&itemChangeLog.getPrevious_Name()!=null)
		{
		if (!itemChangeLog.getName().equalsIgnoreCase(itemChangeLog.getPrevious_Name())) {
			s = "Name....... was: " + itemChangeLog.getPrevious_Name() + ".......is: " + itemChangeLog.getName()+"  ";
			sb.append(s);
		}
		}
		if(itemChangeLog.getDescription()!=null &&itemChangeLog.getPrevious_Description()!=null)
		{
			
		if (!itemChangeLog.getDescription().equalsIgnoreCase(itemChangeLog.getPrevious_Description())) {
			s = "Description....... was: " + itemChangeLog.getPrevious_Description() + ".......is: " + itemChangeLog.getDescription()+"  ";
			sb.append(s);
		}
		}
		
		if(itemChangeLog.getWarrantyTypeId()!=null &&itemChangeLog.getPrevious_WarrantyTypeId()!=null)
		{
		if (!itemChangeLog.getWarrantyTypeId().equals(itemChangeLog.getPrevious_WarrantyTypeId())) {
			s = "WarrantyTypeId....... was: " + itemChangeLog.getPrevious_WarrantyTypeId() + ".......is: "
					+ itemChangeLog.getWarrantyTypeId()+"  ";
			sb.append(s);
		}
		}
		if(itemChangeLog.getWarrantyExpiration()!=null &&itemChangeLog.getPrevious_WarrantyExpiration()!=null)
		{
		if (itemChangeLog.getWarrantyExpiration().compareTo(itemChangeLog.getPrevious_WarrantyExpiration())!=0) {
			s = "WarrantyExpiration....... was: " + itemChangeLog.getPrevious_WarrantyExpiration() + ".......is: "
					+ itemChangeLog.getWarrantyExpiration()+"  ";
			sb.append(s);
		}
		}
		if(itemChangeLog.getSerialNumber()!=null &&itemChangeLog.getPrevious_SerialNumber()!=null)
		{
		if (!itemChangeLog.getSerialNumber().equalsIgnoreCase(itemChangeLog.getPrevious_SerialNumber())) {
			s = "SerialNumber....... was: " + itemChangeLog.getPrevious_SerialNumber() + ".......is: " + itemChangeLog.getSerialNumber()+"  ";
			sb.append(s);
		}
		}
		if(itemChangeLog.getModelNumber()!=null &&itemChangeLog.getPrevious_ModelNumber()!=null)
		{
		if (!itemChangeLog.getModelNumber().equalsIgnoreCase(itemChangeLog.getPrevious_ModelNumber())) {
			s = "ModelNumber....... was: " + itemChangeLog.getPrevious_ModelNumber() + ".......is: " + itemChangeLog.getModelNumber()+"  ";
			sb.append(s);
		}
		}
		if(itemChangeLog.getMeanTimeBetweenService()!=null &&itemChangeLog.getPrevious_MeanTimeBetweenService()!=null)
		{
		if (!itemChangeLog.getMeanTimeBetweenService().equals(itemChangeLog.getPrevious_MeanTimeBetweenService())) {
			s = "MeanTimeBetweenService....... was: " + itemChangeLog.getPrevious_MeanTimeBetweenService() + ".......is: "
					+ itemChangeLog.getMeanTimeBetweenService()+"  ";
			sb.append(s);
		}
		}
		List<AttributeChangeLog> attributeChangeLogList = itemChangeLog.getAttributeChangeLog();
		if(!attributeChangeLogList.isEmpty()) {

		for (AttributeChangeLog attributeChangeLog : attributeChangeLogList) {
			if (!attributeChangeLog.getValue().equalsIgnoreCase(attributeChangeLog.getPrevious_Value())) {
				s = attributeChangeLog.getName() + ".......was: " + attributeChangeLog.getPrevious_Value() + ".......is: "
						+ attributeChangeLog.getValue()+"  ";
				sb.append(s);
			}
		}
}
		String finalString = sb.toString();
		return finalString;
	}


	public String getActionComment(Journal journal, String action) {
		
		String moduleType=journal.getModuleType();
		String actionComment=null;
		if(action.equals(GoTracratConstants.CREATE_NOTE_TYPE))
				{
			if (moduleType != null && !moduleType.isEmpty()) {
				if (moduleType.equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
					actionComment="Company Note: "+journal.getEntityname()+" Added By"+journal.getEnteredby();
				} else if (moduleType.equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
					actionComment="Location: "+journal.getLocationname()+ " Note: "+journal.getEntityname()+" Added By "+journal.getEnteredby();
				} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
					actionComment="Item "+journal.getItemTypeName()+":"+journal.getItemTag()+ " Note: "+journal.getEntityname()+" Added By "+journal.getEnteredby();
				}
				}
				}else if(action.equals(GoTracratConstants.UPDATE_NOTE_TYPE))
				{
					if (moduleType != null && !moduleType.isEmpty()) {
						if (moduleType.equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
							actionComment="Company Note: "+journal.getEntityname()+" Updated By"+journal.getEnteredby();
						} else if (moduleType.equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
							actionComment="Location: "+journal.getLocationname()+ " Note: "+journal.getEntityname()+" Updated By "+journal.getEnteredby();
						} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
							actionComment="Item "+journal.getItemTypeName()+":"+journal.getItemTag()+ " Note: "+journal.getEntityname()+" Updated By "+journal.getEnteredby();
						}
				}
				
				}else if(action.equals(GoTracratConstants.DELETE_NOTE_TYPE))
				{
						if (journal.getEntitytypeid().equals(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID())) {
							actionComment="Company Note: "+journal.getEntityname()+" Deleted By "+journal.getEnteredby();
						} else if (journal.getEntitytypeid().equals(EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID())) {
							actionComment="Location: "+journal.getLocationname()+ " Note: "+journal.getEntityname()+" Deleted By "+journal.getEnteredby();
						} else if (journal.getEntitytypeid().equals(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID())) {
							actionComment="Item "+journal.getItemTypeName()+":"+journal.getItemTag()+ " Note: "+journal.getEntityname()+" Deleted By "+journal.getEnteredby();
						}
				}
				
				
		return actionComment;
	}

	

	@Override
	public String delete(Integer journalId, UserLog userLog, Journal journalTemp) {
		Optional<Journal> journalOptional=journalRepository.findById(journalId);
		Journal journal = null;
		if(journalOptional.isPresent()) {
			journal = journalOptional.get();
		}
		if (journal != null) {
			journalTemp.setEntityname(journal.getEntityname());
			journalTemp.setEntitytypeid(journal.getEntitytypeid());
			 String actionComment=getActionComment(journalTemp, GoTracratConstants.DELETE_NOTE_TYPE);
	         userLog =  GotracratUtility.setUserLogData(userLog,journalTemp.getEnteredby(), journal.getCompanyid(),ModulesEnum.NOTES.getModule(),actionComment);
			journalRepository.deleteById(journal.getJournalid());
			return GoTracratConstants.NOTES_DELETED;
		}
		userLog.setCanNotInsert(true);
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_JOURNAL_iD +journalId);
	}

}
