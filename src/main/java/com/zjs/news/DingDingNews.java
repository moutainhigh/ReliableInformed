package com.zjs.news;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author 李文
 * @create 2020-03-03 16:13
 **/
public class DingDingNews extends AbstractNotifier
{

    private Expression text;

    public DingDingNews(String url) {
        super(url);
        String strs = "{\n" +
                "    \"actionCard\": {\n" +
                "        \"title\": \"可靠消息服务通知\", \n" +
                "        \"text\": \"![screenshot](http://www.zjs.com.cn/u/cms/www/201508/10152643lor1.jpg) \n" +
                " ### #{#name} \\n 地址 ：#{#url}  \\n  #{#data}\", \n" +
                "        \"hideAvatar\": \"0\", \n" +
                "        \"btnOrientation\": \"0\", \n" +
                "        \"singleURL\" : \"#{#url}\"\n" +
                "    }, \n" +
                "    \"msgtype\": \"actionCard\"\n" +
                "}";
        SpelExpressionParser parser = new SpelExpressionParser();
        this.text = parser.parseExpression(strs, ParserContext.TEMPLATE_EXPRESSION);
    }


    @Override
    public String getNews(String name, String url, String data) {
        EvaluationContext ctx = new StandardEvaluationContext();
        //设置两个变量
        ctx.setVariable("name", name);
        ctx.setVariable("url", url);
        ctx.setVariable("data", data);
        return text.getValue(ctx, String.class);
    }


}
