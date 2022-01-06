package com.anitop.notice.vo;

import java.util.Date;
import lombok.Data;

/** @author 배고은 **/

@Data
public class NoticeVO {
	
	private long no;
	private String title;
	private String content;
	private String id;
	private Date writeDate;
	
}
