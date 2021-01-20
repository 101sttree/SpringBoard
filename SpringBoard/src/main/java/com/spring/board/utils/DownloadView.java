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
		//��Ʈ�ѷ��� ���� �޾ƿ� downloadFile�̸��� ������ ���� ��ü�� ��´�.
		//file ���� ������ ������ ��� �ִ�.
        if(file != null) 
        {
            String fileName = null;
            //���� �̸��� �ϴ� ����.
            String userAgent = request.getHeader("User-Agent");
            //
            if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1)
            	//userAgent �ȿ� MSIE �� ���ų� Trident ������
            {
                fileName = URLEncoder.encode(file.getName(), "utf-8").replaceAll("\\+", "%20");;
                //���ϸ��� ���ڵ��ϰ� + ��ȣ�� %20���� �ٲ۴�?
            }
            else if(userAgent.indexOf("Chrome") > -1)
            	//�װ� �ƴϰ� userAgent �� Chrome�� ������ �� ũ�Һ������ϰ��
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
            	//�̷� ������� ���ϸ��� ������.
            }
            else 
            {
            	fileName = new String(file.getName().getBytes("utf-8"));
            	//�װ͵� �ƴϸ� �� ������� ���ϸ��� ������.
            }
            response.setContentType(getContentType());
            //���� ȭ���� ����Ʈ Ÿ���� �����´�.
            response.setContentLength((int)file.length());
            //���ϸ��� ���̸� �����´�.
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
            //����� �����Ѵ�. 
            response.setHeader("Content-Transfer-Encoding", "binary");
            //����� �����Ѵ�.
            
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
