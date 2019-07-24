$(document).ready(function(){
//	upgradePacked();
	$('[data-toggle="tooltip"]').tooltip();//input框提示初始化
	ListUpgradePacked();
});

function searchTable(){
	$('#dataTable').DataTable().ajax.reload();
}

function saveInfo(){
	var fileName=$("#fileName").val();
	var brand=$("#brand").val();
	var test=$("input[name='test']").val();
	var sn=$("#sn").val();
	var hardwareVersion=$("#hardwareVersion").val();
	var versionInfo=$("#versionInfo").val();
	var upgradeProduce=$("#upgradeProduce").val();
	var upgradeType=$("#upgradeType").val();
	var upgradeVersion=$("#upgradeVersion").val();
	var upgradePath=$("#upgradePath").val();
	var upgradeNumber=$("#upgradeNumber").val();
	var reboot=$("#reboot").val();
	var productModel = $("#productModel").val();
	var md5 = $("#md5").val();
	var intelligenceType = $("#intelligenceType").val();
	var upgradePathEnd = upgradePath.substring(upgradePath.lastIndexOf("."));//这个是获取后缀名
	//名称检验
	var nameReg = /^[a-zA-Z0-9\-_\u4e00-\u9fa5]{1,64}$/;//字母数字中文-_1-64位
	if(fileName.trim() ==null || fileName.trim() ==""){
		$('#fileName').parent().parent().removeClass("has-success");
		$('#fileName').parent().parent().addClass("has-error");
		$("#fileName").attr("data-original-title", "名称不能为空");
		$("#fileName").focus();
		return ;
	} else if(!nameReg.test(fileName)){
		$('#fileName').parent().parent().removeClass("has-success");
		$('#fileName').parent().parent().addClass("has-error");
		$("#fileName").attr("data-original-title", "名称不合法！名称中只能包含数字,英文,中文,下划线,中划线！");
		$("#fileName").focus();
		return ;
	}else{
		$('#fileName').parent().parent().removeClass("has-error");
	}
	/*
	if(sn.trim() ==null || sn.trim() ==""){
		$('#sn').parent().parent().removeClass("has-success");
		$('#sn').parent().parent().addClass("has-error");
		$("#sn").attr("data-original-title", "SN号不能为空");
		$("#sn").focus();
		return ;
	}else{
		$('#sn').parent().parent().removeClass("has-error");
	}*/
	
	if(hardwareVersion.trim() ==null || hardwareVersion.trim() ==""){
		$('#hardwareVersion').parent().parent().removeClass("has-success");
		$('#hardwareVersion').parent().parent().addClass("has-error");
		$("#hardwareVersion").attr("data-original-title", "硬件版本号不能为空");
		$("#hardwareVersion").focus();
		return ;
	}else{
		$('#hardwareVersion').parent().parent().removeClass("has-error");
	}
	
	
	if(upgradeVersion.trim() ==null || upgradeVersion.trim() ==""){
		$('#upgradeVersion').parent().parent().removeClass("has-success");
		$('#upgradeVersion').parent().parent().addClass("has-error");
		$("#upgradeVersion").attr("data-original-title", "版本不能为空");
		$("#upgradeVersion").focus();
		return ;
	}else{
		$('#upgradeVersion').parent().parent().removeClass("has-error");
	}
	if((test.trim() ==null || test.trim() =="")&&(upgradePath.trim() ==null || upgradePath.trim() =="")){
		$('#upgradePath').parent().parent().removeClass("has-success");
		$('#upgradePath').parent().parent().addClass("has-error");
		$("#upgradePath").attr("data-original-title", "升级包和升级包路径不能同时为空!（小于5M上传升级包；大于5M不支持上传，填写升级包路径。）");
		$("#upgradePath").focus();
		return ;
	}else if(upgradePath.trim() != null && upgradePath.trim() != "" && upgradePathEnd.trim() !=".pkg"){
		$('#upgradePath').parent().parent().removeClass("has-success");
		$('#upgradePath').parent().parent().addClass("has-error");
		$("#upgradePath").attr("data-original-title", "请选择.pkg文件");
		$("#upgradePath").focus();
		return ;
	}else{
		$('#upgradePath').parent().parent().removeClass("has-error");
		if(test.trim()!=""&& typeof($("input[name='test']")) != "undefined"&& test.trim()!= null){
			$("#upgradePackageForm").ajaxSubmit(function(message) {
				// 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容
					var obj = jQuery.parseJSON(message);
					if(obj.success){
//						BootstrapDialog.show({
//							type:BootstrapDialog.TYPE_SUCCESS, 
//				            title: "提示",
//				            message: '升级打包成功！',
//				            draggable: true
//				        });
//						ListUpgradePacked();
						insertUpgradePacked(fileName,brand,test,sn,hardwareVersion,versionInfo,upgradeProduce,upgradeType,upgradeVersion,"",upgradeNumber,reboot,productModel,md5,intelligenceType);
					}else{
						BootstrapDialog.show({
							type:BootstrapDialog.TYPE_DANGER, 
					        title: "提示",
					        message: '升级打包失败！'+obj.msg,
					        draggable: true
						});
						$('#dataTable').DataTable().ajax.reload(null, false);
					}
					 $("input[name='test']").clone().val("") ;

				});
				
		}else if(upgradePath.trim() !=null && upgradePath.trim() !=""){
			insertUpgradePacked(fileName,brand,test,sn,hardwareVersion,versionInfo,upgradeProduce,upgradeType,upgradeVersion,upgradePath,upgradeNumber,reboot,productModel,md5,intelligenceType);
		}
	}
	$('#upgradePackageModal').modal('hide');
}

function insertUpgradePacked(fileName,brand,test,sn,hardwareVersion,versionInfo,upgradeProduce,upgradeType,upgradeVersion,upgradePath,upgradeNumber,reboot,productModel,md5,intelligenceType){//alert(reboot+upgradeNumber);
	//xml
	var lockXmlDoc = $.parseXML("<upgradeTemplate></upgradeTemplate>");
    var $xmlLock = $( lockXmlDoc );
	var template = $('#templateXmlData').val();
	var xmlDoc = $.parseXML(template);
	var $xml = $( xmlDoc );
	$xmlLock.find("upgradeTemplate").append("<fileName>"+fileName+"</fileName>");
	$xmlLock.find("upgradeTemplate").append("<brand>"+brand+"</brand>");
	$xmlLock.find("upgradeTemplate").append("<upgradeProduce>"+upgradeProduce+"</upgradeProduce>");
	$xmlLock.find("upgradeTemplate").append("<upgradeType>"+upgradeType+"</upgradeType>");
	$xmlLock.find("upgradeTemplate").append("<sn>"+sn+"</sn>");
	$xmlLock.find("upgradeTemplate").append("<hardwareVersion>"+hardwareVersion+"</hardwareVersion>");
	$xmlLock.find("upgradeTemplate").append("<upgradeVersion>"+upgradeVersion+"</upgradeVersion>");
	$xmlLock.find("upgradeTemplate").append("<upgradeNumber>"+upgradeNumber+"</upgradeNumber>");
	$xmlLock.find("upgradeTemplate").append("<reboot>"+reboot+"</reboot>");
	$xmlLock.find("upgradeTemplate").append("<productModel>"+productModel+"</productModel>");
	$xmlLock.find("upgradeTemplate").append("<intelligenceType>"+intelligenceType+"</intelligenceType>");
	$xmlLock.find("upgradeTemplate").append("<versionInfo>"+versionInfo+"</versionInfo>");
	$xmlLock.find("upgradeTemplate").append("<MD5>"+md5+"</MD5>");
	var rsXmlData=(new XMLSerializer()).serializeToString($xmlLock[0])
	
	$.ajax({
		type: 'post',
	    url: '/upgradePacked/upgrade-packed',
	    data: {fileName:fileName,brand:brand,test:test,sn:sn,hardwareVersion:hardwareVersion,versionInfo:versionInfo,
	    	upgradeProduce:upgradeProduce,upgradeType:upgradeType,upgradeVersion:upgradeVersion,upgradePath:upgradePath,
	    	xmlData: rsXmlData},
	    dataType: "json",
	    cache:false, 
	    success: function(data, textStatus){
//	    	var obj = jQuery.parseJSON(data);
	    	if(data.success){
	    		emptyUpgradePacked();
	    		ListUpgradePacked();
    			BootstrapDialog.show({
					type:BootstrapDialog.TYPE_SUCCESS, 
		            title: "提示",
		            message: '升级打包成功！',
		            draggable: true
		        });   
				return ;
	    	}else{
	    		BootstrapDialog.show({
					type:BootstrapDialog.TYPE_DANGER, 
		            title: "提示",
		            message: '操作失败！'+data.msg,
		            draggable: true
		        });   
				return ;
	    	}
	    	 $('#dataTable').DataTable().ajax.reload(null, false);
	    } ,
	    error : function(XMLHttpRequest, textStatus, errorThrown){    
	    	console.log("XMLHttpRequest.readyState:"+XMLHttpRequest.readyState+" textStatus:"+textStatus+" errorThrown:"+errorThrown); 
	    	
	     }
		
	});
	
}
/*
function upgradePacked() {
	$("#dataTable tbody").html("");
	$.ajax({
		type: 'get',
	    url: '/upgradePacked/listUpgradePacked',
	    dataType: "json",
	    cache:false, 
	    success: function(data, textStatus){
	    	console.log(data);
	    	var obj = eval(data);
	    	returnVal=data.records; 
	        	
	    	$("#tableJson").val(JSON.stringify(returnVal));
	    	$("#exampleTablePagination").attr("data",JSON.stringify(returnVal));
	    	 $.each(data.records, function(i, item) {
	    		 $("#dataTableA tbody").append(
	    				 "<tr> <th>" +'<input type="checkbox" value="' + item.id + '" id="checkbox_'+item.id+'"/>'+ "</th>"+
	    				 "<th>" + item.compressionPack + "</th>" +
	    				 "<th>" + (new Date(item.packTime)).Format("yyyy-MM-dd hh:mm:ss")   + "</th>" +
	    				 "<th>" + (item.status=="1"?"新上传":"已下载") + "</th>" +
	    				 "<th>" + '<a class="btn" title="下载"  href ="javascript:void(0)"  onclick="javascript:doDownload(\''+item.id+'\');">下载</a>' + "</th>"+
	    				 "</tr>");
   		  	});
	    } ,
	    error : function(XMLHttpRequest, textStatus, errorThrown){    
	    	console.log("XMLHttpRequest.readyState:"+XMLHttpRequest.readyState+" textStatus:"+textStatus+" errorThrown:"+errorThrown); 
	    	
	     }
		
	}); 
	
}*/

function ListUpgradePacked(){
    /*----------- BEGIN datatable CODE -------------------------*/
	$('#dataTable').dataTable({
//		"bFilter": false,
//        "order": [[ 0, "asc" ]],
//		"bProcessing" :false ,
//		"scrollCollapse": true,
//		"processing": false,
        "sAjaxSource": '/upgradePacked/upgrade-packed',
        "oLanguage": {
        	"sLengthMenu": "每页显示 _MENU_ 条记录",
        	"sZeroRecords": "抱歉， 没有找到",
        	"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
        	"sInfoEmpty": "没有数据",
        	"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
        	"oPaginate": {
        	"sFirst": "首页",
        	"sPrevious": "前一页",
        	"sNext": "后一页",
        	"sLast": "尾页"
        	},
        	"sZeroRecords": "没有检索到数据",
        	"sProcessing": "<img src='./loading.gif' />"
        	},
//        "sPaginationType": "two_button",
        "sServerMethod":"get",
//        "bServerSide" : true,
        	searching:false, //去掉搜索框
        	"destroy" : true,
        "aoColumns" : [
                       {"mData" : "id"},
                       {"mData" : "compressionPack"},
                       {"mData" : "packTime"},
                       {"mData" : "status"},
                       {"mData" : "compressionPackPath"},
                       {"mData" : "id"}
                       ],
        "aoColumnDefs": [
                         {
                        	 "orderable": false,//禁用排序
                             "targets": [0],
                             "width": "5%",
                             "render": function(data, type, full) {
                                 return '<label class="checkbox"><input type="checkbox" value="' + full.id + "#" + full.compressionPack+'" title="' + data + '" id="checkbox_'+data+'"/></label>';
                             }
                         },{
                             "targets": [1],
                             "width": "15%",
                             "render": function(data, type, full) {
                                 return data;
                             }
                         },{
                             "targets": [2],
                             "width": "15%",
                             "render": function(data, type, full) {
                                 return (new Date(full.packTime)).Format("yyyy-MM-dd hh:mm:ss");
                             }
                         },{
                             "targets": [3],
                             "width": "15%",
                             "render": function(data, type, full) {
                                 return full.status=="1"?"新上传":"已下载";
                             }
                         },{
                             "targets": [4],
                             "width": "25%",
                             "render": function(data, type, full) {
                                 return data;
                             }
                         },{
                             "targets": [5],
                             "width": "25%",
                             "render": function(data, type, full) {
                                 /*return '<a class="" href="javascript:void(0)" onclick="javascript:showPageA(\'/view/resourcePool/resourcePool.jsp?domain='+full.domainName+'\');" >' + data + '</a>';*/
                                 return '<a class="btn" title="下载"  href ="javascript:void(0)"  onclick="javascript:doDownload(\''+full.id+'\');">下载</a>'
                                 +'<a class="btn" title="总部传输配置"  href ="javascript:void(0)"  onclick="javascript:sendPackage(\''+full.id+'\');">总部传输配置</a>'
                                 +'<a class="btn" title="删除"  href ="javascript:void(0)"  onclick="javascript:deletePackage(\''+full.id+'\');">删除</a>';
                             }
                         }
                     ],
        "fnServerData" : function(sSource, aoData, fnCallback) {
        	$.ajax({
	        	  "type" : 'get',
	        	  "url" : sSource,
	        	  "dataType" : "json",
	        	  "data" : {
	        		  compressionPack:$("#searchZipName").val(),
	                  aoData : JSON.stringify(aoData)
		      	  	},
		      	  	"success" : function(resp) {
		      	  		fnCallback(resp);
		      	  		$("#dataTable").removeAttr("style");
			      	  	$("#dataTable tbody input:checkbox").click(function(){
				      	  	if(!$(this).is(':checked')){
				      				$("#tableAcheckbox").removeAttr('checked');
				      			}else{
				      			}
			      		});
			      	  	
		      	  	}
        	});
        }

    });
	 $("#dataTable thead input:checkbox").click(function(){
			if($(this).is(':checked')){
				$("#dataTable tbody input:checkbox").prop("checked",true);
			}else{
				$("#dataTable tbody input:checkbox").removeAttr('checked');
			}
	 });
}

function emptyUpgradePacked(){
	$("#fileName").val("");
	$("#brand").val("启明");
	$("input[name='test']").val("");
	$("#sn").val("");
	$("#hardwareVersion").val("");
	$("#versionInfo").val("");
	$("#upgradeVersion").val("");
	$("#upgradePath").val("");
	$("#upgradeNumber").val("");
	$("#reboot").val("0");
	$("#productModel").val("");
	$("#intelligenceType").val("");
}

Date.prototype.Format = function (fmt) {
    var o = {  
        "M+": this.getMonth() + 1, //月份  
        "d+": this.getDate(), //日  
        "h+": this.getHours(), //小时  
        "m+": this.getMinutes(), //分  
        "s+": this.getSeconds(), //秒  
        "q+": Math.floor((this.getMonth() + 3) / 3) //季度  
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
};  

function doDownload(id){
	try{ 
        var elemIF = document.createElement("iframe");   
        elemIF.src = '/upgradePacked/doDownload?id='+id;   
        elemIF.style.display = "none";   
        document.body.appendChild(elemIF);  
        setTimeout('searchTable()',4000);
    }catch(e){ 

    } 
}

function upgradePackage(){
	$('#upgradePackageModal').modal('show');
}

var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function fileChange(target,id) {
	var fileSize = 0;
	var filetypes =[".pkg"];
	var filepath = target.value;
	var filemaxsize = 1024*5;//5M
	if(filepath){
		var isnext = false;
		var fileend = filepath.substring(filepath.lastIndexOf("."));
		if(filetypes && filetypes.length>0){
			for(var i =0; i<filetypes.length;i++){
				if(filetypes[i]==fileend){
					isnext = true;
					break;
				}
			}
		}
		if(!isnext){
			BootstrapDialog.show({
				type:BootstrapDialog.TYPE_DANGER, 
	            title: "提示",
	            message: '不接受此文件类型！请上传pkg文件。',
	            draggable: true
	        });  
			target.value ="";
			return false;
		}
	}else{
		return false;
	}
	if (isIE && !target.files) {
		var filePath = target.value;
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		if(!fileSystem.FileExists(filePath)){
		BootstrapDialog.show({
			type:BootstrapDialog.TYPE_DANGER, 
            title: "提示",
            message: '附件不存在，请重新输入！',
            draggable: true
        }); 
		return false;
		}
		var file = fileSystem.GetFile (filePath);
		fileSize = file.Size;
	} else {
		fileSize = target.files[0].size;
	}
	
	var size = fileSize / 1024;
	if(size>filemaxsize){
//		alert("附件大小不能大于"+filemaxsize/1024+"M！");
		BootstrapDialog.show({
			type:BootstrapDialog.TYPE_DANGER, 
            title: "提示",
            message: "附件大小不能大于"+filemaxsize/1024+"M！",
            draggable: true
        }); 
		target.value ="";
		return false;
	}
	if(size<=0){
		BootstrapDialog.show({
			type:BootstrapDialog.TYPE_DANGER, 
            title: "提示",
            message: "附件大小不能为0M！",
            draggable: true
        }); 
		target.value ="";
		return false;
	}
}


function sendPackage(id){
	$('#sendupgradePackageModal').modal('show');
	$("#packsgeId").val(id);
	$("#hqUsername").val("");
	$("#hqPassword").val("");
	$("#hqIp").val("");
	$("#hqAddress").val("/home/upgrade/headquarters/scan/");
}

function saveHqInfo(){
	var hqUsername=$("#hqUsername").val();
	var hqPassword=$("#hqPassword").val();
	var hqIp=$("#hqIp").val();
	var id=$("#packsgeId").val();
	var hqAddress=$("#hqAddress").val();
	//ip校验
	
	if(hqIp.trim() ==null || hqIp.trim() ==""){
		$('#hqIp').parent().parent().removeClass("has-success");
		$('#hqIp').parent().parent().addClass("has-error");
		$("#hqIp").attr("data-original-title", "IP不能为空");
		$("#hqIp").focus();
		return ;
	}else{
		$('#hqIp').parent().parent().removeClass("has-error");
	}
	
	if(hqAddress.trim() ==null || hqAddress.trim() ==""){
		$('#hqAddress').parent().parent().removeClass("has-success");
		$('#hqAddress').parent().parent().addClass("has-error");
		$("#hqAddress").attr("data-original-title", "路径不能为空");
		$("#hqAddress").focus();
		return ;
	}else{
		$('#hqAddress').parent().parent().removeClass("has-error");
	}
	
	//名称检验
	var nameReg = /^[a-zA-Z0-9\-_\u4e00-\u9fa5]{1,64}$/;//字母数字中文-_1-64位
	if(hqUsername.trim() ==null || hqUsername.trim() ==""){
		$('#hqUsername').parent().parent().removeClass("has-success");
		$('#hqUsername').parent().parent().addClass("has-error");
		$("#hqUsername").attr("data-original-title", "名称不能为空");
		$("#hqUsername").focus();
		return ;
	} else if(!nameReg.test(hqUsername)){
		$('#hqUsername').parent().parent().removeClass("has-success");
		$('#hqUsername').parent().parent().addClass("has-error");
		$("#hqUsername").attr("data-original-title", "名称不合法！名称中只能包含数字,英文,中文,下划线,中划线！");
		$("#hqUsername").focus();
		return ;
	}else{
		$('#hqUsername').parent().parent().removeClass("has-error");
	}
	
	if(hqPassword.trim() ==null || hqPassword.trim() ==""){
		$('#hqPassword').parent().parent().removeClass("has-success");
		$('#hqPassword').parent().parent().addClass("has-error");
		$("#hqPassword").attr("data-original-title", "密码不能为空");
		$("#hqPassword").focus();
		return ;
	}else{
		$('#hqPassword').parent().parent().removeClass("has-error");
	}
	
	$.ajax({
		type: 'post',
	    url: '/upgradePacked/send-package',
	    data: {id:id,password:hqPassword,user:hqUsername,ip:hqIp,path:hqAddress},
	    dataType: "json",
	    cache:false, 
	    success: function(data, textStatus){
	    	if(data.success){
	    		$('#sendupgradePackageModal').modal('hide');
    			BootstrapDialog.show({
					type:BootstrapDialog.TYPE_SUCCESS, 
		            title: "提示",
		            message: '文件传输提交成功！',
		            draggable: true
		        });   
    			$('#dataTable').DataTable().ajax.reload(null, false);
				return ;
	    	}else{
	    		BootstrapDialog.show({
					type:BootstrapDialog.TYPE_DANGER, 
		            title: "提示",
		            message: '文件传输提交失败！'+data.msg,
		            draggable: true
		        });   
	    		$('#dataTable').DataTable().ajax.reload(null, false);
				return ;
	    	}
	    	 
	    } ,
	    error : function(XMLHttpRequest, textStatus, errorThrown){    
	    	console.log("XMLHttpRequest.readyState:"+XMLHttpRequest.readyState+" textStatus:"+textStatus+" errorThrown:"+errorThrown); 
	    }
	});
}


function deletePackage(id){
    BootstrapDialog.show({
        message: '你确定要删除记录和文件吗?',
        cssClass: 'vc-modal-dialog',
        type:BootstrapDialog.TYPE_WARNING,
        title: "提示",
        buttons: [
            {
	            label: '确定',
	            action: function(dialogRef) {
	//            	$("#tipsinfo").html('删除中 <i class="fa fa-spinner fa-spin"></i>');
	//            	$("#tipsinfo").removeClass('collapse').addClass('in');
	            	dialogRef.close();
	            	$.ajax({
	            		type: 'post',
	            	    url: '/upgradePacked/delete-package',
	            	    data: {id:id},
	            	    dataType: "json",
	            	    cache:false, 
	            	    success: function(data, textStatus){
	            	    	if(data.success){
	                			BootstrapDialog.show({
	            					type:BootstrapDialog.TYPE_SUCCESS, 
	            		            title: "提示",
	            		            message: '删除成功！',
	            		            draggable: true
	            		        });   
	                			$('#dataTable').DataTable().ajax.reload(null, false);
	            				return ;
	            	    	}else{
	            	    		BootstrapDialog.show({
	            					type:BootstrapDialog.TYPE_DANGER, 
	            		            title: "提示",
	            		            message: '删除失败！'+data.msg,
	            		            draggable: true
	            		        });   
	            	    		$('#dataTable').DataTable().ajax.reload(null, false);
	            				return ;
	            	    	}
	            	    } ,
	            	    error : function(XMLHttpRequest, textStatus, errorThrown){    
	            	    	console.log("XMLHttpRequest.readyState:"+XMLHttpRequest.readyState+" textStatus:"+textStatus+" errorThrown:"+errorThrown); 
	            	    }
	            	});
	            }
            },
            {
	            label: '取消',
	            action: function(dialogRef) {
	                dialogRef.close();
	            }
            }
        ]
    });
}

