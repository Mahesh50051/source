package com.kafka.microservice.businessobjects;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaDataResponse extends BaseResponseObject
{

	private String searchId;

	private String memberId;

	private String solutionCount;

	private String msgCode;

	@JsonIgnore(true)
	private String responseTmsp;
	
	
	public String getResponseTmsp()
	{
		return responseTmsp;
	}

	public void setResponseTmsp(String responseTmsp) 
	{
		this.responseTmsp = responseTmsp;
	}

	private List<KafkaData> kafkaDataList;

	public String getSearchId()
	{
		return searchId;
	}

	public void setSearchId(String searchId)
	{
		this.searchId = searchId;
	}

	public String getMemberId()
	{
		return memberId;
	}

	public void setMemberId(String memberId)
	{
		this.memberId = memberId;
	}

	public String getSolutionCount()
	{
		return solutionCount;
	}

	public void setSolutionCount(String solutionCount)
	{
		this.solutionCount = solutionCount;
	}

	public String getMsgCode()
	{
		return msgCode;
	}

	public void setMsgCode(String msgCode)
	{
		this.msgCode = msgCode;
	}

    public List<KafkaData> getKafkaDataList() {
      return kafkaDataList;
    }
  
    public void setKafkaDataList(List<KafkaData> kafkaDataList) {
      this.kafkaDataList = kafkaDataList;
    }

	

}
