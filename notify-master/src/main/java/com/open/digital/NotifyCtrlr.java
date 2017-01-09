package com.open.digital;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.open.digital.model.EmailData;
import com.open.digital.model.EmailRes;
import com.open.digital.model.SrvcResModel;
import com.open.digital.util.Const;
import com.open.digital.util.Utils;

/**
 * @author pradyroy
 * @version 1.0
 * @since 2016-11-11
 */
@RestController
@RequestMapping("/notify")
public class NotifyCtrlr {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	protected Utils utils;

	@Autowired
	protected SendmailSrvc sendmailSrvc;

	public NotifyCtrlr() {

	}
	
	@RequestMapping(value="/echo", method = RequestMethod.GET)
	public String echo() {	
		logger.info(utils.getlogid(null, "NotifyCtrlr", "echo") + Utils.LOGDLMTR + utils.getjson("stage", "ctrlrReq"));
		return "Hi, the server time is " + (new Date()).toString() + ".";
	}

	@RequestMapping(value="/sendmail", method = RequestMethod.POST)
	public ResponseEntity<EmailRes> sendmail(@RequestBody EmailData emailData) {
		logger.info(utils.getlogid(null, "NotifyCtrlr", "sendmail") + Utils.LOGDLMTR + utils.getjson("stage", "ctrlrReq"));
		ResponseEntity<EmailRes> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		SrvcResModel<EmailRes> srvcResModel = new SrvcResModel<EmailRes>();
		srvcResModel = sendmailSrvc.sendmail(utils.getlogid(null, "NotifyCtrlr", "sendmail"), emailData);

		headers.add(Const.HTTP_HEADER_RES_MSG_KEY, srvcResModel.getResmsgkey());
		headers.add(Const.HTTP_HEADER_RES_MSG_VAL, srvcResModel.getResmsgval());
		responseEntity = new ResponseEntity<EmailRes>(srvcResModel.getModel(), headers, srvcResModel.getHttpstatus());
		logger.debug(utils.getlogid(null, "NotifyCtrlr", "sendmail") + Utils.LOGDLMTR + utils.getjson("stage", "ctrlrRes"));
		return responseEntity;
	}
}
