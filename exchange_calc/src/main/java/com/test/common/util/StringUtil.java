package com.test.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cozyhill
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class StringUtil {

	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * 전달된 문자열을 src_enc 방식에서 dest_enc 방식으로 변환한다.
	 *
	 * @param String
	 *            str 변환시킬 문자열
	 * @param String
	 *            src_enc 원래 문자의 encoding방식
	 * @param String
	 *            des_enc 변환시킬 encoding방식.
	 * @return String desc_enc 방식으로 변환된 문자열
	 * @throws UnsupportedEncodingException
	 *             : Encoding이 지원되지 않는 문자열 변환시
	 */
	public static String toConvert(String str, String src_enc, String dest_enc)
			throws java.io.UnsupportedEncodingException {
		if (str == null)
			return "";
		else {
			String name = new String(str.getBytes(src_enc), dest_enc);
			return new String(str.getBytes(src_enc), dest_enc);
		}
	}

	/**
	 * Null String을 "" String으로 바꿔준다.
	 *
	 *
	 * @param String
	 *            str Null 문자열
	 *
	 * @return String "' 문자열
	 */
	static public String nullConv(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	static public String nullConv(String str, String str1) {
		if (str == null)
			return str1;
		else
			return str;
	}

	public static String nvl(String str, String str1) {
		if (str == null || str.length() == 0)
			return str1;
		else
			return str;
	}

	public static String delOneChar(String str) {
		if (str == null || str.length() == 1)
			return "";
		return str;
	}

	public static String nvl(Object str) {
		return StringUtil.nvl(str, "");
	}
	public static String nvl(Object str, String str1) {
		if ( null == str )
			return str1;
		else
			return String.valueOf(str);
	}

	/***************************************************************************
	 * 전달된 문자열의 길이를 넘겨온 길이에 맞게 잘라서 넘겨준다. 잘린 String에 Attach String을 덧붙여서 리턴한다.
	 *
	 * @param src
	 *            변환할 문자열
	 **************************************************************************/
	static public String strCut(String src, int str_length, String att_str) {
		int ret_str_length = 0;

		String ret_str = new String("");

		// 현재 환경의 Character length를 구한다.
		String tempMulLanChar = new String("가");
		int lanCharLength = tempMulLanChar.length();
		// Character가 중간에 잘리지 않게 하기위해 넣은 변수
		int multiLanCharIndex = 0;

		for (int i = 0; i < src.length(); i++) {
			ret_str += src.charAt(i);

			if (src.charAt(i) > '~') {
				ret_str_length = ret_str_length + 2 / lanCharLength;
				multiLanCharIndex++;
			} else {
				ret_str_length = ret_str_length + 1;
			}

			if (ret_str_length > str_length
					&& (multiLanCharIndex % lanCharLength) == 0) {
				ret_str += StringUtil.nullConv(att_str);
				break;
			}
		}

		return ret_str;
	}

	public static String webStrCut(String src, int str_length, String att_str) {
		if (src == null && src.length() == 0) {
			return "";
		}
		if (src.length() <= str_length) {
			return src;
		} else {
			return src.substring(0, str_length) + att_str;
		}
	}

	/**
	 * byte[]를 substring 한다.
	 *
	 * @param s_byte
	 *            원본 byte[]
	 * @param s_idx
	 *            start index
	 * @param e_idx
	 *            end index
	 * @return String substring 후 String으로 리턴
	 */
	public static String byteStrCut(byte[] s_byte, int s_idx, int e_idx) {
		byte r_byte[] = new byte[e_idx - s_idx];
		int y = 0;
		for (int z = s_idx; z < e_idx; z++)
			r_byte[y++] = s_byte[z];

		return new String(r_byte);
	}

	/** 주어진 길이(len)에서 문자열(s)의 길이를 제외한 우측을 특정 character(padc)로 채워준다. */
	public static String rpad(String s, int len, char padc) {
		int rlen = s.getBytes().length;
		if (rlen >= len) {
			return s;
		}
		StringBuffer sb = new StringBuffer(s);
		for (int i = rlen; i < len; i++)
			sb.append(padc);
		return sb.toString();
	}

	public static String rpad(int srcint, int length, char ch) {
		return rpad(String.valueOf(srcint), length, ch);
	}

	/** 주어진 길이(len)에서 문자열(s)의 길이를 제외한 좌측을 특정 character(padc)로 채워준다. */
	public static String lpad(String s, int len, String padc) {

		return lpad(s, len, padc.toCharArray()[0]);
	}

	/** 주어진 길이(len)에서 문자열(s)의 길이를 제외한 좌측을 특정 character(padc)로 채워준다. */
	public static String lpad(String s, int len, char padc) {
		int rlen = s.getBytes().length;
		if (rlen >= len) {
			return s;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = rlen; i < len; i++)
			sb.append(padc);
		sb.append(s);
		return sb.toString();
	}

	public static String lpad(int srcint, int length, char ch) {
		return lpad(String.valueOf(srcint), length, ch);
	}

	public static String[] split(String str) {
		return split(str, null);
	}

	public static String[] split(String str, String symbol) {
		StringTokenizer st;
		if (symbol != null && symbol.length() > 0) {
			st = new StringTokenizer(str, symbol);
		} else {
			st = new StringTokenizer(str);
		}

		String[] astr = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); astr[i] = st.nextToken(), i++)
			;
		return astr;
	}

	/**
	 * 주민등록번호, 사업자 번호 형태로 리턴하기
	 *
	 * @param rrno
	 * @return
	 */
	public static String formatSidBiz(String sidbizno) {
		if (sidbizno == null) {
			return sidbizno;
		} else if (sidbizno.length() == 13) {
			return sidbizno.substring(0, 6) + "-" + sidbizno.substring(6, 13);
		} else if (sidbizno.length() == 10) {
			return sidbizno.substring(0, 3) + "-" + sidbizno.substring(3, 5)
					+ "-" + sidbizno.substring(5);
		} else {
			return sidbizno;
		}
	}

	/** 년월일 사이에 '.'를 첨가한다. */
	public static String date(String str) {

		String temp = null;
		int len = str.length();

		if (len != 8)
			return str;
		// if ( str.equals("00000000") || (str.trim()).equals("0") ||
		// (str.trim()).equals("")) // 최창권 20060614 수정
		if ((str.trim()).equals("0") || (str.trim()).equals(""))
			return "";
		temp = str.substring(0, 4) + "." + str.substring(4, 6) + "."
				+ str.substring(6, 8);

		return temp;
	}

	/** 년월 사이에 '.'를 첨가한다. */
	public static String dateYM(String str) {

		String temp = null;
		int len = str.length();

		if (len != 6)
			return str;
		if (str.equals("000000") || (str.trim()).equals("0")
				|| (str.trim()).equals(""))
			return "";
		temp = str.substring(0, 4) + "." + str.substring(4, 6);

		return temp;
	}

	/**
	 * 주민번호, 카드번호, 전화번호, 계좌번호 등 주요정보에 대해 * 로 마스킹 처리를 함<br>
	 * 각 번호의 종류에 따라 각각 마스킹 처리를 하며 포맷되거나 포맷 안된 것을 모두 지원함<br>
	 * 결과는 포맷을 하여 보여줌
	 *
	 * @param str
	 *            String , 입력 값
	 * @param type
	 *            int 입력값 구분<br>
	 *            1: 주민번호, 주민번호 뒷자리를 * 로 변환<br>
	 *            1: 사업자번호, 주민번호 뒷자리를 * 로 변환<br>
	 *            2: 카드번호, 카드번호 2번째, 3번째 4자리를 * 로 변환<br>
	 *            3: 전화번호, 마지막 4자리를 * 로 변환<br>
	 *            4: 계좌번호, 마지막 4자리를 * 로 변환<br>
	 *            5: 이메일 주소, 마지막 2자리를 * 로 변환, isFormat 은 무시됨<br>
	 *            6: IP
	 * @param isFormat
	 *            boolean, 출력 데이터 포맷여부, true:포맷, false:포맷 안함
	 * @return String객체
	 */
	public static String getMask(String str, int type, boolean isFormat) {
		int p = 0;
		if (type == 1) { // 주민번호
			if (str == null)
				str = "";
			str = str.trim();
			if ((p = str.indexOf("-")) > -1) {
				if (str.length() == 14) {
					return str.substring(0, p + 1) + "*******";
				} else if (str.length() == 12) {
					return str.substring(0, str.length() - 5) + "*****";
				} else {
					return str;
				}
			} else {
				if (str.length() == 13) {
					if (isFormat) {
						return str.substring(0, 6) + "-*******";
					} else {
						return str.substring(0, 6) + "*******";
					}
				} else if (str.length() == 10) {
					if (isFormat) {
						return str.substring(0, 3) + "-" + str.substring(3, 5)
								+ "-" + "*****";
					} else {
						return str.substring(0, str.length() - 5) + "*****";
					}
				} else {
					return str;
				}
			}
		} else if (type == 2) {
			if ((p = str.indexOf("-")) > -1) {
				if (str.length() != 19) {
					return str;
				} else {
					return str.substring(0, 15) + "***"
							+ str.substring(str.length() - 1);
				}
			} else {
				if (str.length() != 16) {
					return str;
				} else {
					if (isFormat) {
						return str.substring(0, 4) + "-" + str.substring(4, 8)
								+ "-" + str.substring(8, 12) + "-***"
								+ str.substring(str.length() - 1);
					} else {
						return str.substring(0, 12) + "***"
								+ str.substring(str.length() - 1);
					}
				}
			}
		} else if (type == 3) { // 전화번호 포맷
			if (isFormat) {
				// str = getTelnoFormat(str);
			}
			if (str.trim().length() < 5) {
				return "";
			}
			if (str.indexOf("-") != -1) {
				return str.substring(0, str.lastIndexOf("-")) + "-****";
			} else {
				return str.substring(0, str.length() - 4) + "****";
			}
		} else if (type == 4) {
			if (isFormat) {
				str = getAcctFormat(str);
			}
			return str.substring(0, str.length() - 4) + "***"
					+ str.substring(str.length() - 1);
		} else if (type == 5) {
			String id = "";
			if ((p = str.indexOf("@")) != -1) {
				id = str.substring(0, p);
				if (id.length() == 0) { // 이메일 주소 에러
					return "";
				} else if (id.length() == 1) {
					return "*" + str.substring(p);
				} else if (id.length() == 2) {
					return "**" + str.substring(p);
				} else {
					String m = id.substring(0, 2);
					for (int i = 0; i < p - 2; i++)
						m += "*";
					m += str.substring(p);
					return m;
				}
			} else { // 이메일 주소 에러
				return "";
			}
		} else if (type == 6) {
			return str.substring(0, (str.lastIndexOf(".") + 1)) + "***";
		} else {

//			AS-IS : ConfigClass.consoleOutPrint(StringUtil.class, 347, "지원되지 않는 type 입니다. type=[" + type + "]");
//			XMaLL spring 에서는 ConfigClass.consoleOutPrint 사용 안함.
			if(logger.isDebugEnabled()){
				logger.debug("지원되지 않는 type 입니다. type=[" + type + "]");
			}

			return "지원되지 않는 type 입니다.";
		}
	}

	/**
	 * 주민번호, 카드번호, 전화번호, 계좌번호 등 주요정보에 대해 * 로 마스킹 처리를 함<br>
	 * 각 번호의 종류에 따라 각각 마스킹 처리를 하며 포맷되거나 포맷 안된 것을 모두 지원함<br>
	 * 결과는 포맷을 하여 보여줌
	 *
	 * @param str
	 *            String , 입력 값
	 * @param type
	 *            int 입력값 구분<br>
	 *            1: 주민번호, 주민번호 뒷자리를 * 로 변환<br>
	 *            2: 카드번호, 카드번호 2번째, 4번째 4자리를 * 로 변환<br>
	 *            3: 전화번호, 마지막 4자리를 * 로 변환<br>
	 *            4: 계좌번호, 마지막 7자리를 * 로 변환<br>
	 *            5: 이메일 주소, 첫 2자리를 * 로 변환, isFormat 은 무시됨<br>
	 * @return String객체
	 */
	public static String getMask(String str, int type) {
		return getMask(str, type, false);
	}

	/**
	 * 입력된 문자열에서 왼쪽의 0 을 제거<br>
	 * 00000004 => Integer.parseInt("00000004") = 4 ;
	 *
	 * @param str
	 *            String
	 * @return String객체
	 **/
	public static String removePadZero(String str) {
		return removePadZero(str, 0);
	}

	/**
	 * 입력된 문자열에서 왼쪽의 0 을 입력받은 갯수만큼만 남기고 제거, 전화번호 지역번호, 국번 검사에 사용 함<br>
	 * 지역번호 0 하나만 남김, cnt=1, 국번 앞의 0을 모두 삭제 cnt=0
	 *
	 * @param str
	 *            String
	 * @param cnt
	 *            int : 0을 남길 갯수
	 * @return String객체
	 **/
	public static String removePadZero(String str, int cnt) {
		int zerocnt = 0;
		for (int i = 0; i < str.length() - 1; i++) {
			if (str.substring(i, i + 1).equals("0")) {
				zerocnt++;
			} else {
				break;
			}
		}
		if (zerocnt > cnt) {
			return str.substring(zerocnt - cnt);
		} else {
			return str;
		}
	}

	/**
	 * 입력된 String 을 원하는 길이만큼 채울 문자로 채워준다<br>
	 * default 로 왼쪽에 blank 로 채움
	 *
	 * @param str
	 *            String 원본 데이터
	 * @param len
	 *            int 최종 데이터 길이
	 * @return String객체
	 */
	public static String getPadString(String str, int len) {
		return getPadString(str, len, " ", 0);
	}

	/**
	 * 입력된 String 을 원하는 길이만큼 채울 문자로 채워준다<br>
	 * default 로 왼쪽에 채움
	 *
	 * @param str
	 *            String 원본 데이터
	 * @param len
	 *            int 최종 데이터 길이
	 * @param padstr
	 *            String 채울 문자열
	 * @return String객체
	 */
	public static String getPadString(String str, int len, String padstr) {
		return getPadString(str, len, padstr, 0);
	}

	/**
	 * 입력된 String 을 원하는 길이만큼 채울 문자로 채워준다<br>
	 * 입력된 lr 에 따라 왼쪽 또는 오른쪽에 채워준다 default 왼쪽
	 *
	 * @param str
	 *            String 원본 데이터
	 * @param len
	 *            int 최종 데이터 길이
	 * @param padstr
	 *            String 채울 문자열, 하나의 문자로 하는것을 권장
	 * @param lr
	 *            int 왼쪽에 채울지 오른쪽에 채울지 지정, 0:left(default), 1:right
	 * @return String객체
	 */
	public static String getPadString(String str, int len, String padstr, int lr) {
		String rtnstr = "";
		if (str.length() >= len) {
			return str;
		} else {
			if (lr == 1) {
				rtnstr = str + padstr;
			} else {
				rtnstr = padstr + str;
			}
			if (rtnstr.length() < len) {
				return getPadString(rtnstr, len, padstr, lr);
			} else {
				return rtnstr;
			}
		}
	}

	/**
	 * 2byte 코드 또는 10보다 작은 수의 String 등의 경우 1Byte 인 경우에 앞자리에 0 을 붙여 2Byte 를 만들어 줌
	 *
	 * @param str
	 *            String Data
	 * @return 2byte String
	 */
	public static String getPadZero(String str) {
		return getPadString(str, 2, "0");
	}

	/**
	 * 2byte 코드 또는 10보다 작은 수의 String 등의 경우 1Byte 인 경우에 앞자리에 0 을 붙여 2Byte 를 만들어 줌
	 *
	 * @param num
	 *            int Data
	 * @return 2byte String
	 */
	public static String getPadZero(int num) {
		return getPadString(Integer.toString(num), 2, "0");
	}

	/** 년월일 사이에 '-'를 첨가한다. */
	public static String dateDash(String str) {

		String temp = null;
		int len = str.length();

		if (len == 4) {
			temp = str.substring(0, 3) + "-" + str.substring(3, 5);
		} else if (len == 6) {
			temp = str.substring(0, 4) + "-" + str.substring(4, 6);
		} else if (len == 8) {
			temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
					+ str.substring(6, 8);
		} else {
			temp = str;
		}
		if (temp.equals("0000-00-00") || temp.equals("0000-00")
				|| temp.equals("00-00"))
			temp = "";

		return temp;

	}

	/**
	 * 년월일 한글로 표시한다. by hjun 2000.12.06
	 */
	public static String dateHan(String str) {
		String temp = null;
		int len = str.length();

		if (len != 8)
			return str;
		if (str.equals("00000000") || (str.trim()).equals("0")
				|| (str.trim()).equals(""))
			return "";
		temp = str.substring(0, 4) + "년 "
				+ Integer.parseInt(str.substring(4, 6)) + "월 "
				+ Integer.parseInt(str.substring(6, 8)) + "일";

		return temp;
	}

	/** 년월 한글로 표시한다 */
	public static String dateHanYM(String str) {

		String temp = null;
		int len = str.length();

		if (len != 6)
			return str;
		if ((str.equals("000000")) || (str.trim()).equals("0"))
			return "";
		temp = str.substring(0, 4) + "년 "
				+ Integer.parseInt(str.substring(4, 6)) + "월";

		return temp;
	}

	/** 시분초 사이에 ':'를 첨가한다. */
	public static String time(String str) {

		String temp = null;
		// Hjun edit.. 2000.11.1
		if (str == null)
			return "";
		int len = str.length();

		if (len != 6)
			return str;

		temp = str.substring(0, 2) + ":" + str.substring(2, 4) + ":"
				+ str.substring(4, 6);
		return temp;
	}

	/** 시분 사이에 ':'를 첨가한다. */
	public static String time2(String str) {

		String temp = null;
		int len = str.length();

		if (len < 4)
			return str;

		temp = str.substring(0, 2) + ":" + str.substring(2, 4);
		return temp;
	}

	/**
	 * 시분 한글로 표시한다 by hjun 2000.12.06
	 */
	public static String timeHanHM(String str) {

		String temp = null;
		int len = str.length();

		if (len > 6)
			return str;

		temp = Integer.parseInt(str.substring(0, 2)) + "시 "
				+ Integer.parseInt(str.substring(2, 4)) + "분";

		return temp;
	}

	/** 주민등록번호 또는 사업자번호에 '-'를 첨가한다. */
	public static String regnNo(String str) {

		String temp = null;
		str = str.trim();
		int len = str.length();

		if ((len != 13) && (len != 10))
			return str;

		// 사업자번호
		if (len == 10)
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-"
					+ str.substring(5, 10);

		// 주민등록번호
		if (len == 13)
			temp = str.substring(0, 6) + "-" + str.substring(6, 13);

		return temp;
	}

	/** 은행 계좌번호에 '-'를 첨가한다. */
	public static String getAcctFormat(String str) {

		String temp = null;

		if (str.trim().length() == 16) {
			if (str.startsWith("0000")) { // 계좌 xxx-xx-xxxxxxx
				temp = str.substring(4);
				temp = temp.substring(0, 3) + "-" + temp.substring(3, 5) + "-"
						+ temp.substring(5);
			} else { // 카드 xxxx-xxxx-xxxx-xxxx
				temp = str.substring(0, 4) + "-" + str.substring(4, 8) + "-"
						+ str.substring(8, 12) + "-" + str.substring(12);
			}
		} else if (str.trim().length() == 12) {
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-"
					+ str.substring(5);
		} else {
			temp = str;
		}
		return temp;
	}

	/** 은행 가맹점번호에 '-'를 첨가한다. */
	public static String getMercFormat(String str) {

		return getMercFormat(str, "08");

	}

	/** 은행 가맹점번호에 '-'를 첨가한다. */
	public static String getMercFormat(String str, String type) {

		String temp = str;

		// 주류가맹점
		if (type.equals("08")) {
			if (str.trim().length() == 12) {
				temp = str.substring(0, 2) + "-" + str.substring(2, 5) + "-"
						+ str.substring(5);
			} else {
				temp = str;
			}
		}
		// 비자가맹점
		else if (type.equals("07")) {
			if (str.trim().length() == 12) {
				temp = str.substring(0, 3) + "-" + str.substring(3, 6) + "-"
						+ str.substring(6);
			} else {
				temp = str;
			}
		}
		return temp;
	}

	/** 매출채권번호 혹은 어음관리번호에 '-'를 첨가한다. */
	public static String noteNo(String str) {

		String temp = null;

		if (str.trim().length() != 12)
			return str;

		temp = str.substring(0, 3) + "-" + str.substring(3, 4) + "-"
				+ str.substring(4, 12);

		return temp;
	}

	/** 카드번호에 '-'를 첨가한다. */
	public static String cardNo(String str) {

		String temp = null;

		if (str.trim().length() != 16)
			return str;

		temp = str.substring(0, 4) + "-" + str.substring(4, 8) + "-"
				+ str.substring(8, 12) + "-" + str.substring(12, 16);

		return temp;
	}

	/** 숫자에 컴마표기 */
	public static String getMoneyFormat(int nVal) {
		String displayForm = new DecimalFormat("#,###,###,###").format(nVal);
		return displayForm;
	}

	public static String getMoneyFormat(long nVal) {
		String displayForm = new DecimalFormat("#,###,###,###").format(nVal);
		return displayForm;
	}

	public static String getMoneyFormat(double nVal) {
		String displayForm = new DecimalFormat("#,###,###,###").format(nVal);
		return displayForm;
	}

	public static String getMoneyFormat(String strVal) {
		if (strVal == null || strVal.trim().equals("")
				|| strVal.trim().replaceAll(",", "").equalsIgnoreCase("null"))
			return "0";

		double nVal = Double.parseDouble(strVal);
		String displayForm = new DecimalFormat("#,###,###,###").format(nVal);
		return displayForm;
	}

	public static String getMoneyUnFormat(String strVal) {
		if (strVal == null || strVal.trim().equals("")
				|| strVal.trim().equalsIgnoreCase("null"))
			return "0";

		String displayForm = replace(strVal, ",", "");
		displayForm = replace(displayForm, ".", "");
		return displayForm;
	}

	public static String getDollarFormat(double dVal) {
		String displayForm = new DecimalFormat("#,###,###,##0.00").format(dVal);
		return displayForm;
	}

	public static String getDollarFormat2(String strVal) {
		String displayForm = new DecimalFormat("#,###,###,##0.000")
				.format(strVal);
		return displayForm;
	}

	/**
	 * 전체 String값(value)중 파라미터(unit)에해당하는 단위만큼 뒤에서 substring하고 나머지는 세자리마다
	 * ','를찍는다. ( sign 은 포함하지 않는다. )
	 * ------------------------------------------------------------------- ex>
	 * makeValueAsCurrency("0",3)일 경우 0.000을 리턴. makeValueAsCurrency("1",3)일 경우
	 * 0.001을 리턴. makeValueAsCurrency("1000",3)일 경우 1.000을 리턴.
	 * makeValueAsCurrency("1110000",3)일 경우 1,110.000을 리턴.
	 * makeValueAsCurrency("1110000.1",3)일 경우 1,110,000.100을 리턴.
	 * --------------------------------------------------------------------
	 *
	 * @param n
	 *            String 형식을 바꾸고자 하는 문자열
	 * @param unit
	 *            int 소수점 이하로 표시하고자 하는 문자열 개수
	 *
	 * @return 문자열 원하는 길이만큼을 소수점 이하로 처리하고, 남은 문자열은 세자리 마다 ","를 찍은 문자열
	 */
	public static String makeValueAsCurrency(String n, int unit) {

		String o = "";
		String p = "";

		n = (N2S(n, "")).trim();
		n = replace(n, ",", "");

		if (n.indexOf(".") != -1) {
			for (int i = 0; i < unit; i++)
				n = "0" + n + "0";
			o = n.substring(0, n.indexOf("."));
			p = n.substring(n.indexOf(".") + 1, n.indexOf(".") + 1 + unit);
		} else {
			for (int i = 0; i < unit; i++)
				n = "0" + n;
			o = n.substring(0, n.length() - unit);
			p = n.substring(n.length() - unit, n.length());
		}

		o = getCurrency(o);

		if (unit > 0) {
			return o + "." + p;
		} else {
			return o;
		}

	}

	/**
	 * 숫자형문자를 돈형식으로 표기하여 가져온다. ( sign 은 포함하지 않는다. )
	 *
	 * @param n
	 *            String 금액으로 표현하고자하는 문자열
	 * @return String객체 표현처리된 문자열
	 */
	public static String getCurrency(String n) {

		// java에서 기본제공하는것의 문제점때문에 개별적으로 만들었다.
		// DecimalFormat dollarFmt = new DecimalFormat("##########.00");
		// dollarFmt.format(amount)

		String o = "";
		String p = "";

		n = (N2S(n, "")).trim();
		o = replace(o, " ", "");
		o = replace(o, ",", "");
		o = replace(o, "+", "");

		if (n.indexOf(".") != -1) {
			o = n.substring(0, n.indexOf("."));
			p = n.substring(n.indexOf(".") + 1, n.length());
		} else {
			o = n;
		}

		int zeroNum = 0;
		boolean zeroChk = false;
		for (int i = 0; i < o.length(); i++) {
			if (!o.substring(i, i + 1).equals("0")) {
				zeroNum = i;
				zeroChk = true;
				break;
			}
		}

		if (zeroNum != 0)
			o = o.substring(zeroNum, o.length());
		else if (zeroChk == false)
			o = "0";

		int tLen = o.length();
		String tMoney = "";
		for (int i = 0; i < tLen; i++) {
			if (i != 0 && (i % 3 == tLen % 3))
				tMoney += ",";
			if (i < tLen)
				tMoney += o.charAt(i);
		}

		if (p.length() > 0)
			tMoney += "." + p;
		// if ( nFlag == false ) tMoney = "-"+tMoney;

		return tMoney;
	}

	/** 주어진 길이만큼 '0'을 채운다. */
	public static String fillZero(double nVal, int length) {
		String tmp = "";
		String displayForm = "";
		for (int i = 0; i < length; i++) {
			tmp += "0";
		}

		if (tmp != null) {
			displayForm = new DecimalFormat(tmp).format(nVal);
		}

		return displayForm;
	}

	/** 주어진 길이만큼 space를 오른쪽에 채운다. */
	public static String fillSpace(String strVal, int nLength) {
		return getPadString(strVal, nLength, " ", 1);
	}

	/** 문자열 내에서 원하는 문자열 replace 하기 */
	public static String replace(String oldString, String from, String to) {
		String newString = oldString;
		int offset = 0;
		while ((offset = newString.indexOf(from, offset)) > -1) {
			StringBuffer temp = new StringBuffer(newString.substring(0, offset));
			temp.append(to);
			temp.append(newString.substring(offset + from.length()));
			newString = temp.toString();
			offset++;
		}
		return newString;
	}

	/**
	 * 해당 문자열 중 <code>oldString</code>을 <code>newString</code>로 변환
	 *
	 * @param str
	 *            문자열
	 * @param oldString
	 *            옛날 문자
	 * @param newString
	 *            새 문자
	 * @return 바뀐 문자
	 */
	public static String replaceAll(String str, String oldString,
			String newString) {
		if (str != null) {
			for (int i = 0; (i = str.indexOf(oldString, i)) >= 0; i += newString
					.length()) {
				str = str.substring(0, i) + newString
						+ str.substring(i + oldString.length());
			}
			return str;
		} else
			return "";
	}

	/**
	 * String이 null이거나 "" 이면 true 를 리턴한다.
	 */
	public static boolean N2B(String str) {
		return N2S(str, "").equals("") ? true : false;
	}

	/**
	 * String이 null이거나 "" 이면 "" 를 리턴한다.
	 */
	public static String N2S(String str) {
		return N2S(str, "");
	}

	/**
	 * String이 null이거나 "" 이면 outstr 을 리턴한다.
	 */
	public static String N2S(String str, String outStr) {
		String result = "";
		try {
			if (str == null || str.trim().equals(""))
				result = outStr;
			else
				result = str;
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * String이 null이면 0로 아니면 Integer로 변환하여 값 리턴
	 */
	public static int N2I(String str) throws NumberFormatException {
		if (str == null || str.trim().equals(""))
			return 0;
		else
			return Integer.parseInt(str.trim());
	}

	/**
	 * String이 null이면 0로 아니면 Double로 변환하여 값 리턴
	 */
	public static double N2D(String str) throws NumberFormatException {
		if (str == null) {
			return 0.0;
		} else {
			if (str.trim().equals("")) {
				return 0.0;
			} else {
				return Double.parseDouble(str.trim());
			}
		}
	}

	/**
	 * String이 null이면 0로 아니면 long으로 변환하여 값 리턴
	 */
	public static long N2L(String str) throws NumberFormatException {
		if (str == null) {
			return 0;
		} else {
			if (str.trim().equals("")) {
				return 0;
			} else {
				return Long.parseLong(str.trim());
			}
		}
	}

	/**
	 * 한글깔끔히 짜르기.<br>
	 * 한글인코딩 처리하시고 한글,영문 모두 1byte로 계산해서 처리하시면 됩니다.<br>
	 * &lt;%@ page contentType="text/html; charset=UTF-8" %&gt;
	 */
	public static String strClip(String s, int iLen) {

		try {
			byte[] bRtn = s.getBytes();

			int iCut = iLen;
			int iMaxCnt = s.length();

			for (int i = 0; i < bRtn.length; i++) {
				if (i > iLen - 1)
					break;

				if (bRtn[i] < 0) {
					iCut--;
					i++;
				}
			}
			if (iCut > iMaxCnt)
				return s;

			return s.substring(0, iCut);

		} catch (Exception e) {
			return s;
		}
	}

	/** 하나의 긴 String을 주어진 integer array의 순서대로 tokenize */
	public static String[] parsing(int[] delim, String str) {

		int i, offset = 0;

		// 한글문제로 인하여 byte로 변환한 후 처리
		byte[] input = str.getBytes();
		String[] output = new String[delim.length];
		String temp;

		for (i = 0; i < delim.length; i++) {
			temp = new String(input, offset, delim[i]);
			output[i] = temp;
			offset += delim[i];
		}
		return output;
	}

	/**
	 * KSC5601 형식의 문자인지 검증한다.
	 */
	public static boolean isKSC5601(String str) {
		boolean rtn = false;
		try {
			rtn = str.equals(new String(str.getBytes("KSC5601"), "KSC5601")) ? true
					: false;
		} catch (UnsupportedEncodingException ue) {
			rtn = false;
		}
		return rtn;
	}

	/**
	 * 전각기호로 변환
	 *
	 * @param String
	 *            str 입력문자
	 * @return String 전각 변환문자.
	 */
	public static String convert2ByteChar(String str) {
		return convert2ByteChar(str, 0);
	}

	/**
	 * 전각기호로 변환후 maxlen만큼 전각space를 체운뒤 maxlen만큼 잘라낸다.
	 *
	 * @param String
	 *            str 입력문자
	 * @param int maxlen maxlength 만큰 체울 byte길이
	 * @return String 전각 변환문자.
	 */
	public static String convert2ByteChar(String str, int maxlen) {

		String UNICODE_HALF_FULL_ASCII[][] = { { " ", "　" }, { "!", "！" },
				{ "\"", "”" }, { "#", "＃" }, { "$", "＄" }, { "%", "％" },
				{ "&", "＆" }, { "'", "’" }, { "(", "（" }, { ")", "）" },
				{ "*", "＊" }, { "+", "＋" }, { ",", "，" }, { "-", "－" },
				{ ".", "．" }, { "/", "／" }, { "0", "０" }, { "1", "１" },
				{ "2", "２" }, { "3", "３" }, { "4", "４" }, { "5", "５" },
				{ "6", "６" }, { "7", "７" }, { "8", "８" }, { "9", "９" },
				{ ":", "：" }, { ";", "；" }, { "<", "＜" }, { "=", "＝" },
				{ ">", "＞" }, { "?", "？" }, { "@", "＠" }, { "A", "Ａ" },
				{ "B", "Ｂ" }, { "C", "Ｃ" }, { "D", "Ｄ" }, { "E", "Ｅ" },
				{ "F", "Ｆ" }, { "G", "Ｇ" }, { "H", "Ｈ" }, { "I", "Ｉ" },
				{ "J", "Ｊ" }, { "K", "Ｋ" }, { "L", "Ｌ" }, { "M", "Ｍ" },
				{ "N", "Ｎ" }, { "O", "Ｏ" }, { "P", "Ｐ" }, { "Q", "Ｑ" },
				{ "R", "Ｒ" }, { "S", "Ｓ" }, { "T", "Ｔ" }, { "U", "Ｕ" },
				{ "V", "Ｖ" }, { "W", "Ｗ" }, { "X", "Ｘ" }, { "Y", "Ｙ" },
				{ "Z", "Ｚ" }, { "[", "［" }, { "\\", "￥" }, { "]", "］" },
				{ "^", "＾" }, { "_", "＿" }, { "`", "‘" }, { "a", "ａ" },
				{ "b", "ｂ" }, { "c", "ｃ" }, { "d", "ｄ" }, { "e", "ｅ" },
				{ "f", "ｆ" }, { "g", "ｇ" }, { "h", "ｈ" }, { "i", "ｉ" },
				{ "j", "ｊ" }, { "k", "ｋ" }, { "l", "ｌ" }, { "m", "ｍ" },
				{ "n", "ｎ" }, { "o", "ｏ" }, { "p", "ｐ" }, { "q", "ｑ" },
				{ "r", "ｒ" }, { "s", "ｓ" }, { "t", "ｔ" }, { "u", "ｕ" },
				{ "v", "ｖ" }, { "w", "ｗ" }, { "x", "ｘ" }, { "y", "ｙ" },
				{ "z", "ｚ" }, { "{", "｛" }, { "|", "｜" }, { "}", "｝" },
				{ "~", "～" } };

		int len = str.length();
		int index = 0;
		int cnt = 1;
		String returnValue = "";

		while (index < len) {
			if (str.charAt(index) < 256) {
				for (int i = 0; i < UNICODE_HALF_FULL_ASCII.length; i++) {
					if (str.substring(cnt - 1, cnt).equals(
							UNICODE_HALF_FULL_ASCII[i][0]))
						returnValue += UNICODE_HALF_FULL_ASCII[i][1];
				}
			} else { // 2바이트 문자라면...
				returnValue += str.substring(cnt - 1, cnt);
			}
			index++;
			cnt++;
		}

		int curLen = returnValue.getBytes().length;
		for (int i = curLen; maxlen > 0 && i < maxlen; i++) {
			returnValue += "　";
			i++;
		}

		// 입력한 길이만큼 잘라낸다.
		returnValue = new String(returnValue.getBytes(), 0, maxlen);

		return returnValue;

	}

	/**
	 * 특수기호 변환<br>
	 *
	 * <pre>
	 * [&] ==> &#38 / [(] ==> &#40 / [)] ==> &#41 / [<] ==> &lt / [>] ==> &gt;
	 * </pre>
	 *
	 * @param String
	 *            str HTML 기호값
	 * @return String 변환값
	 */
	public static String xssCheck(String str) {
		String temp = "";
		if (str != null) {
			temp = str.replaceAll("[&]", "&#38;");
			temp = temp.replaceAll("[(]", "&#40;");
			temp = temp.replaceAll("[)]", "&#41;");
			temp = temp.replaceAll("[<]", "&lt;");
			temp = temp.replaceAll("[>]", "&gt;");

		}
		return temp;
	}

	/*
	 * 전각 space trim()
	 *
	 * @param String str 입력String
	 *
	 * @return String trim된 String
	 */
	public static String UNItrim(String str) {
		if (str == null) {
			return "";
		}
		str = str.replaceAll("　", " ");
		return str.trim();

	}

	/**
	 * 검색시 반각→전각(우편번호 검색시)
	 *
	 * @param source
	 * @return
	 */
	public static String getEmString(String source) {

		String before = "0123456789";
		String next = "０１２３４５６７８９";
		String returnValue = "";

		for (int i = 0; i < source.length(); i++) {
			if (before.indexOf(source.substring(i, i + 1)) > -1) {
				returnValue += next.substring(before.indexOf(source.substring(
						i, i + 1)),
						before.indexOf(source.substring(i, i + 1)) + 1);
			} else
				returnValue += source.substring(i, i + 1);
		}

		return returnValue;
	}

	/**
	 * random number 를 발생시켜 [a-z]+숫자 로 이루어진 문자열을 반환한다.
	 *
	 * @return String Random 숫자로 구성된 문자열.
	 */
	public static String getRandomPassword() {
		char[] alpha = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', };

		Random random = new Random();

		double rn = Math.random();
		int ri = Math.abs(random.nextInt());
		int key = ri % 26;

		String s = String.valueOf(rn);

		int pos = s.indexOf(".");

		if (pos > 0) {
			s = s.substring(pos + 1);
		}

		s = String.valueOf(alpha[key]) + s;
		s = s.substring(0, 10);

		return s;
	}

	/**
	 * 상기된 문장부호 제거
	 *
	 * @param String
	 *            str 입력문자
	 * @return String 변환값
	 */
	public static String deletePunctuation(String str) {

		if (str == null) {
			return "";
		}

		str = str.replaceAll("'", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll("\\?", "");

		return str;
	}

	public static String numberFormat(String str) {
		if(str == null || str.length() == 0){
			return "0";
		}
		return numberFormat(str, "#,###,###,###");
	}

	public static String numberFormat(String str, String format) {
		double nVal = Double.parseDouble(str);
		String displayForm = new DecimalFormat(format).format(nVal);
		return displayForm;
	}

	public static String dateFormat(String str) throws ParseException {
		return dateFormat(str, "yyyy-MM-dd");
	}

	public static String dateFormat(String str, String format)
			throws ParseException {
		if (str == null || str.length() == 0) {
			return "<<정보없음>>";
		}
		if (str.length() == 8) {
			str += "000000";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = sdf.parse(str);
		SimpleDateFormat sdf2 = new SimpleDateFormat(format);
		return sdf2.format(date);
	}

	public static String substr(String str, int begin) {
		if (str == null || str.equals(""))
			return "";
		return substr(str, str.length());
	}

	public static String substr(String str, int begin, int length) {
		String tmp = "";
		if (str == null || str.equals(""))
			return "";
		try {
			tmp = str.substring(begin, begin + length);
		} catch (Exception e) {
			tmp = "";
		}
		return tmp;
	}

	public static String[] explode(String str, String delim) {
		String[] tmp = new String[] { "", "", "", "", "" };
		if (str == null || str.equals(""))
			return tmp;
		if (str.split(delim).length != 0)
			tmp = str.split(delim);
		return tmp;

	}

	public static String implode(String delim, String[] str) {
		String tmp = "";
		if (str == null)
			return tmp;
		for (int i = 0; i < str.length; i++) {

			if(null != str[i] && !"".equals(str[i])){
				if (i == 0)
					tmp = tmp + str[i];
				else
					tmp = tmp + delim + str[i];
			}

		}
		return tmp;

	}

	public static int array_sum(String[] str) {
		int tmp = 0;
		if (str == null)
			return tmp;
		for (int i = 0; i < str.length; i++) {
			tmp += Integer.parseInt(str[i]);
		}
		return tmp;

	}

	public static String sprintf(String format, String str) {
		String strformat = "";

		if (str.equals(""))
			return strformat;

		if (format.indexOf("d") > -1) {
			strformat = String.format(format, new Integer(str));
		} else if (format.indexOf("f") > -1) {
			strformat = String.format(format, new Double(str));
		} else if (format.indexOf("t") > -1) {
			strformat = String.format(format, new Date(str));
		}
		return strformat;
	}

	public static String checkedOption(String result, String select) {
		String strChk = "";
		if (!"".equals(result)) {
			if ( 0 != ( Short.parseShort(result)&(Short.parseShort(select)) ) ){
				strChk = "checked";
			}
		}
		return strChk;
	}
	public static String checkedOption2(String[] result, String select) {
		String strChk = "";
		if (null != result && !"".equals(result) && result.length>0) {
			for(int i=0;i<result.length;i++){
				if(result[i].equals(select)){
					strChk = "checked";
				}
			}
		}
		return strChk;
	}
	/**
	 * db에 저장된 style 받아서 font스타일 완성하여 보내줌
	 */
	public static String makeTytleStyle(String txt, String style) {
		String str = "";
		String style_color = "";
		String style_size = "";
		String style_bold = "";
		String[] arrstr = split(style, "|");
		for (int i = 0; i < arrstr.length; i++) {
			if (checkIwant("^C:", arrstr[i])) {
				style_color = arrstr[i].substring(3);
			} else if (checkIwant("^S:", arrstr[i])) {
				style_size = arrstr[i].substring(3);
			} else if (checkIwant("^B:", arrstr[i])) {
				style_bold = arrstr[i].substring(3);
			}
		}
		if ("bord".equals(style_bold)) {
			str = "<font color='" + style_color + "' size='" + style_size
					+ "'><b>" + txt + "</b></font>";
		} else {
			str = "<font color='" + style_color + "' size='" + style_size
					+ "'>" + txt + "</font>";
		}
		if (arrstr.length == 0)
			str = txt;

		return str;
	}

	/**
	 * 내가 원하는 값인지 체크 checkStr 이 String이 str에 포함되어있는지 확인
	 *
	 * @param str
	 * @param checkStr
	 * @return
	 */
	public static boolean checkIwant(String checkStr, String str) {
		boolean flag = false;
		if (str.indexOf(checkStr) > -1) {
			flag = true;
		}
		return flag;
	}

	/*
	 * 만약 length가 0이 아닐경우 넘어온 String을 substring하여 넘겨준다.
	 */
	public static String strlen(String txt, int length) {
		String str = "";
		if (length == 0) {
			str = txt;
		} else {
			if (txt.length() <= length)
				str = txt.substring(0, txt.length());
			else
				str = txt.substring(0, length) + "...";
		}
		return str;
	}

	/***************************************************************************
	 * HTML의 <input type="select" name의 option 값을 selected 하기 위한 값셋팅
	 *
	 * @return 변환된 문자열
	 **************************************************************************/
	public static String selected(String select, String option) {
		if (select.equals(option))
			return "selected";
		else
			return "";
	}

	/***************************************************************************
	 * HTML의 <input type="select" name의 option 값을 selected 하기 위한 값셋팅
	 *
	 * @return 변환된 문자열
	 **************************************************************************/
	public static String checked(String check, String option) {
		if (check.equals(option))
			return "checked";
		else
			return "";
	}

	/***************************************************************************
	 * str로 배열을 만들고 만든 배열의 수가 len보다 작은지 큰지 판단하여 txt.substring(0,len)이냐
	 * txt.substring(0,len+1)인지를 판단
	 *
	 * @return 변환된 문자열
	 **************************************************************************/
	public static String strcut(String str, int len) {
		String txt = "";
		txt = str.replace(" ", "");
		if (txt.length() > len) {
			len = len - 2;
			int pos = len;
			for (pos = len; pos > 0 && txt.charAt(pos - 1) >= 127; pos--)
				;
			if ((len - pos) % 2 == 0)
				txt = txt.substring(0, len);
			else
				txt = txt.substring(0, len + 1);
		}
		return txt;
	}

	/***************************************************************************
	 * arr_str배열에 str값이 존재하면 return 값에 "checked" 반환
	 *
	 * @return 변환된 문자열
	 **************************************************************************/
	public static String checked(String[] arr_str , String str) {
		String checked = "";

		if(arr_str != null){
			for (int i = 0; i < arr_str.length; i++) {
				if(str.equals(arr_str[i])){
					checked = "checked";
					break;
				}
			}
		}

		return checked;
	}

	/***************************************************************************
	 * arr_str배열에 str값이 존재하면 return 값에 "checked" 반환
	 *
	 * @return 변환된 문자열
	 **************************************************************************/
	public static String checked2(String _str, String str) {
		String checked = "";

		if(str.equals(_str)){
			checked = "checked";
		}

		return checked;
	}


	public static String subStrDesc(String str) {
		if(null != str){
			int len = str.length();
			str = str.substring(len);
		}else{
			return str;
		}
		return str;
	}


	/**
	 * String UnEscape 처리
	 *
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * String Escape 처리
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}


	/**
	 * String skin 디렉토리 파일명 가져오기
	 * @param src : 원래 프로그램 명,
	 * @param skin : 스킨 템플릿 명,
	 * @param after : 스킨명을 삽입할 위치
	 * @return
	 */
	public static String getSkinURI(String src, String skin, int after) {
		StringBuilder sb = new StringBuilder(src);
		sb.insert(after, "/skin/"+skin);

		return sb.toString();
	}

	/**
	 * String skin 디렉토리 파일명 가져오기
	 * @param src : 원래 프로그램 명,
	 * @param skin : 스킨 템플릿 명,
	 * @param after : 스킨명을 삽입할 위치명
	 * @return
	 */
	public static String getSkinURI(String src, String skin, String after) {
		int idx = src.indexOf(after)+after.length();
		return getSkinURI(src, skin, idx);
	}

	public static String replaceNull(String value) {
		return replaceNull(value, "");
	}

	/**
	 * MethodName: replaceNull<br>
	 * 널값이면 repStr 값을, 그렇지않으면 trim()한 값을 리턴
	 * 
	 * @param value java.lang.String
	 * @param repStr java.lang.String
	 * @return java.lang.String
	 */
	public static String replaceNull(String value, String repStr) {
		if (isNull(value) || value.equals("") )
			return repStr;
		
		return getStripHtml(value.trim());
	}
	
	/**
	 * MethodName: isNull<br>
	 * 널이거나 blank이면 true, 그렇지 않으면 false값을 리턴<br>
	 * 
	 * @param value java.lang.String
	 * @return java.lang.boolean
	 */
	public static boolean isNull(String value) {
		if (value == null
			|| value.trim().equals("")
			|| value.toUpperCase().trim().equals("NULL"))
			return true;

		return false;
	}

	/**
	 * 입력받은 문자열에서 HTML을 벗겨버리는 메소드
	 * @param buffer
	 * @return
	 */
	public static String getStripHtml(String buffer) {
		if (buffer == null)
			return "";
		if (buffer.indexOf("<") < 0)
			return buffer;
		int bufLen = buffer.length();
		StringBuffer result = new StringBuffer();
		int i = 0,j = 0,k = 0;
		
		for (; i < bufLen;) {
			j = buffer.indexOf("<", j);
			if (j >= 0) {
				result.append(buffer.substring(i, j));
				k = j;
				j = buffer.indexOf(">", j);
				if(j < 0){
					result.append(buffer.substring(k+1));
					break;
				}
				i = j+1;
			} else
				break;
		}
		result.append(buffer.substring(i));
		return result.toString();
	}
	
	 public static boolean byteCheck(String txt, int standardByte) {
	        if(replaceNull(txt, "").equals("")){
	        	return true; 
	        }
	 
	        // 바이트 체크 (영문 1, 한글 2, 특문 1)
	        int en = 0;
	        int ko = 0;
	        int etc = 0;
	 
	        char[] txtChar = txt.toCharArray();
	        for (int j = 0; j < txtChar.length; j++) {
	            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
	                en++;
	            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
	                ko++;
	                ko++;
	            } else {
	                etc++;
	            }
	        }
	 
	        int txtByte = en + ko + etc;
	        if (txtByte > standardByte) {
	            return false;
	        } else {
	            return true;
	        }
	 
	    }

	 	public static String removeTag(String html) throws Exception {
			return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		}
	 	
	 	public static String removeSpecialCharacters(String str){       
	 	      String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
	 	      str =str.replaceAll(match, " ");
	 	      return str;
	 	 }

	 	
	 	
	 	public static  String randomNumber (int length) {
			int index = 0;
			char[] charSet = new char[] {
				    '0','1','2','3','4','5','6','7','8','9'};
			
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<length; i++) {
				index =  (int) (charSet.length * Math.random());
				sb.append(charSet[index]);
			}
			
			return sb.toString();
			
		}
	 	
	 	public static String convertImageSize(String targetURl,String size) {
	 		String [] imgSplit=targetURl.split("/");
	 		String fileName=imgSplit[imgSplit.length-1];
	 		String convertImageName="scaled-"+size+"-"+fileName;
	 		String completeImage=targetURl.replace(fileName, convertImageName);
	 		
	 		
	 		return completeImage;
	 	}
}
