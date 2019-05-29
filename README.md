# EI-RN6020型用户信息传输装置解码说明
### 本项目暂时完成《城市消防远程监控》系列国家标准实施指南的第六章。
### 此项目只将指南中的数据包解析并封装成对象。
<h3>注:</h3>
<ol>
    <li>指南中传输字节方式是低字节在前高字节在后</li>
    <li>此项目关于部件地址和说明的相关字节暂未作解析仅存储<前低后高></li>
    <li>项目中的时间字节以解析并存储</li>
    <li>此项目使用长轮询,暂未处理缺包,粘包等问题</li>
</ol>

<h3>开发环境</h3>
<table>
    <thead>
        <tr>
            <td><strong>工具</strong></td> 
            <td><strong>版本描述</strong></td> 
        </tr>
    </thead>
    <tbody>
        <tr>
           <td><strong>OS</strong></td> 
           <td><strong>Win10</strong></td> 
        </tr>
        <tr>
           <td><strong>JDK</strong></td> 
           <td><strong>1.8+</strong></td> 
        </tr>
        <tr>
           <td><strong>IDE</strong></td> 
           <td><strong>IntelliJ IDEA 2017.3</strong></td> 
        </tr>
        <tr>
           <td><strong>Maven</strong></td> 
           <td><strong>3.3.9</strong></td> 
        </tr>
   </tbody>
</table>

<h3>后续</h3>
<ol>
    <li>完善缺包,粘包等问题</li>
    <li>优化长轮询</li>
</ol>