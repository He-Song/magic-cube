package song.attendance.model;

import java.util.Date;

import lombok.Data;

/**
 * 打卡记录
 * @author hesong
 *
 */
@Data
public class PunchRecord {

	private Long id;

	private Long staffId;
	
	private String staffName;

	private Date date;
	
	private String recordDate;

	private String startTime;

	private String endTime;
	
	private boolean workingDay;
}
