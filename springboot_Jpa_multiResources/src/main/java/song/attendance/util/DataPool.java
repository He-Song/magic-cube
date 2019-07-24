package song.attendance.util;

import java.util.HashMap;
import java.util.Map;

import song.attendance.model.PatchRecord;
import song.attendance.model.PunchRecord;

public class DataPool {
	/** 打卡记录 */
	public static Map<String, PunchRecord> punchRecords = new HashMap<>();

	/** 补卡记录 */
	public static Map<String, PatchRecord> patchRecords = new HashMap<>();

	/** 加班记录 */
	public static Map<String, PatchRecord> extraworkRecords = new HashMap<>();

	/** 请假记录 */
	public static Map<String, PatchRecord> leaveRecords = new HashMap<>();
}
