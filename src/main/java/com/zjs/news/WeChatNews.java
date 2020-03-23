package com.zjs.news;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author 李文
 * @create 2020-03-06 10:37
 **/
public class WeChatNews extends AbstractNotifier
{
    private Expression text;

    public WeChatNews(String url) {
        super(url);
        String strs = "{\n" +
                "    \"msgtype\": \"news\",\n" +
                "    \"news\": {\n" +
                "       \"articles\" : [\n" +
                "           {\n" +
                "               \"title\" : \"可靠消息服务通知\",\n" +
                "               \"description\" : \"#{#name}\\n地址：#{#url}\\n#{#data}  \",\n" +
                "               \"url\" : \"#{#url}\",\n" +
                "               \"picurl\" : \"http://www.zjs.com.cn/u/cms/www/201508/10152643lor1.jpg\"\n" +
                "           }\n" +
                "        ]\n" +
                "    }\n" +
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
