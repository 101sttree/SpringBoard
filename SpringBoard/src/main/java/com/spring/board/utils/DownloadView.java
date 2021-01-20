package com.spring.board.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel
	(
			Map<String, Object> model,
			HttpServletRequest request,
			HttpServletResponse response
	)
	throws Exception 
	{

		File file = (File)model.get("downloadFile");
		//컨트롤러를 통해 받아온 downloadFile이름의 파일을 파일 객체에 담는다.
		//file 에는 파일의 정보가 담겨 있다.
        if(file != null) 
        {
            String fileName = null;
            //파일 이름은 일단 없다.
            String userAgent = request.getHeader("User-Agent");
            //
            if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1)
            	//userAgent 안에 MSIE 이 없거나 Trident 있으면
            {
                fileName = URLEncoder.encode(file.getName(), "utf-8").replaceAll("\\+", "%20");;
                //파일명을 인코딩하고 + 기호를 %20으로 바꾼다?
            }
            else if(userAgent.indexOf("Chrome") > -1)
            	//그게 아니고 userAgent 에 Chrome가 있으면 즉 크롬브라우저일경우
            {
            	StringBuffer sb = new StringBuffer();
            	for(int i=0; i<file.getName().length(); i++) 
            	{
            		char c = file.getName().charAt(i);
            		if(c > '~') 
            		{
            			sb.append(URLEncoder.encode(""+c, "UTF-8"));
            		}
            		else 
            		{
            			sb.append(c);
            		}
            	}
            	fileName = sb.toString();
            	//이런 방식으로 파일명을 만들어낸다.
            }
            else 
            {
            	fileName = new String(file.getName().getBytes("utf-8"));
            	//그것도 아니면 이 방식으로 파일명을 만들어낸다.
            }
            response.setContentType(getContentType());
            //지금 화면의 콘텐트 타입을 가져온다.
            response.setContentLength((int)file.length());
            //파일명의 길이를 가져온다.
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
            //헤더를 설정한다. 
            response.setHeader("Content-Transfer-Encoding", "binary");
            //헤더를 설정한다.
            
            OutputStream out = response.getOutputStream();
            FileInputStream fis = null;
            try 
            {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
            } 
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if(fis != null)
                {
                    try
                    {
                        fis.close();
                    }
                    catch(Exception e)
                    {
                    	e.printStackTrace();
                    }
                }
                
                if(out != null) {
                	out.flush();
                }
            }
            
        }
	}
}
