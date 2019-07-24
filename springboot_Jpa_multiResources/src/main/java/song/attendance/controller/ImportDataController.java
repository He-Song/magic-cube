package song.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import song.attendance.service.ImportDataService;

@Controller
@RequestMapping(value = "/import")
public class ImportDataController {
	
	@Autowired
	private ImportDataService importDataService;

	/*
	 * 上传考勤excel
	 */
	@RequestMapping(value = "/punchExcel", method = RequestMethod.POST)
	@ResponseBody
	public String importPunchExcel(@RequestParam(value = "file") MultipartFile file) {
		return importDataService.analPunchExcel(file);
	}

	/*
	 * 上传补卡excel
	 */
	@RequestMapping(value = "/patchExcel", method = RequestMethod.POST)
	@ResponseBody
	public String importPatchExcel(@RequestParam(value = "file") MultipartFile file) {
		return importDataService.analPatchExcel(file);
	}

	/*
	 * 上传加班excel
	 */
	@RequestMapping(value = "/extraworkExcel", method = RequestMethod.POST)
	@ResponseBody
	public String importExtraworkExcel(@RequestParam(value = "file") MultipartFile file) {
		return importDataService.analExtraworkExcel(file);
	}

	/*
	 * 上传请假excel
	 */
	@RequestMapping(value = "/leaveExcel", method = RequestMethod.POST)
	@ResponseBody
	public String importLeaveExcel(@RequestParam(value = "file") MultipartFile file) {
		return importDataService.analLeaveExcel(file);
	}
}
