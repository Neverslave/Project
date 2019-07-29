
layui.use(['upload','element','form'], function() {
    var $ = layui.jquery
        , upload = layui.upload
        ,element = layui.element
        ,form = layui.form;
    //选完文件后不自动上传
    var uploadInst =  upload.render({
          elem: '#upload'
        , url: '/upload/'
        ,type:'POST'
        , auto: false
        ,accept:'file'
        //,multiple: true
        ,choose:function (obj) {
            obj.preview(function(index, file, result){
                $('#fileInput').value=file.name;
            })

        }
        , bindAction: '#startUpload'
        , done: function (res) {
            console.log(res)
            options = '';

/*            for (let i = 0; i <res.length ; i++) {
                options += "<option class='' value='" + res[i] + "'>" + res[i] + "</option>";
            }*/
            console.log(options);
            $('#select').append(options);
            form.render();
        }
    });
})


//下载ZIP包
function downloadZip(i){
    node = "input-sheet".concat(i);
    console.log(node)
    title =document.getElementById(node).value;
    console.log(title);

}