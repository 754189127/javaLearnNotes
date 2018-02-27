    @RequestMapping("/cp/getAllUrl")                                   
    @ResponseBody
    public Set<String> getAllUrl(HttpServletRequest request) {
        Set<String> result = new HashSet<String>();
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        
        Map map =  bean.getHandlerMethods();
        Iterator<?> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            RequestMappingInfo info = (RequestMappingInfo)entry.getKey();
            result.add(info.getPatternsCondition() + "=" + entry.getValue());
        }
        logger.info("1{},2{},3{}","in1","2",null);
        return result;
    }

|url|mothed|
| ---------- | ---------- |
|	[/accesslog/save/ajax]	|	public int com.xxxx.eclass.common.web.AccessLogController.AjaxSaveAccessLog(javax.servlet.http.HttpServletRequest,java.lang.String,java.lang.String),	|
|	[/homework/s/post/ajax]	|	public void 
....
