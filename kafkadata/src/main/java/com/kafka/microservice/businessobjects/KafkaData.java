package com.kafka.microservice.businessobjects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaData
{

	private String solIndex;

	private String origin;

	private String destination;

	private String departureDate;

	private String returnDate;

	private String tripDuration;

	private String totalMiles;

	private String mileageSaving;

	private String cabin;
	
	private int clicked = 0; 

	public String getSolIndex()
	{
		return solIndex;
	}

	public void setSolIndex(String solIndex)
	{
		this.solIndex = solIndex;
	}

	public String getOrigin()
	{
		return origin;
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public String getDepartureDate()
	{
		return departureDate;
	}

	public void setDepartureDate(String departureDate)
	{
		this.departureDate = departureDate;
	}

	public String getReturnDate()
	{
		return returnDate;
	}

	public void setReturnDate(String returnDate)
	{
		this.returnDate = returnDate;
	}

	public String getTripDuration()
	{
		return tripDuration;
	}

	public void setTripDuration(String tripDuration)
	{
		this.tripDuration = tripDuration;
	}

	public String getTotalMiles()
	{
		return totalMiles;
	}

	public void setTotalMiles(String totalMiles)
	{
		this.totalMiles = totalMiles;
	}

	public String getMileageSaving()
	{
		return mileageSaving;
	}

	public void setMileageSaving(String mileageSaving)
	{
		this.mileageSaving = mileageSaving;
	}

	public String getCabin()
	{
		return cabin;
	}

	public void setCabin(String cabin)
	{
		this.cabin = cabin;
	}
	
	public int getClicked()
	{
		return clicked;
	}

	public void setClicked(int clicked)
	{
		this.clicked = clicked;
	}

	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{Origin:").append(origin).append("|Destination:").append(destination).append("|Departure:")
				.append(departureDate).append("|Return:").append(returnDate).append("}");
		return builder.toString();
	}

}
