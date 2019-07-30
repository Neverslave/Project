
layui.use(['upload','element','form','layer'], function() {
    var filename ;
    var $ = layui.jquery
        , upload = layui.upload
        ,element = layui.element
        ,layer= layui.layer
        ,form = layui.form;
    //选完文件后不自动上传
    var uploadInst =  upload.render({
          elem: '#upload'
        , url: '/upload'
        , auto: false
        ,accept:'file'
        , bindAction: '#startUpload'
        ,choose:function (obj) {
            obj.preview(function(index, file, result){
                $('#fileInput').val(file.name)
                filename = file.name;
            })

        }
        , done: function (res) {
              layer.msg("上传成功");
                showTitles(res);
                form.render();
        }
    });



    //写入input
    function showTitles(map) {
        let i = 1;
        for( let key in map) {
            if(!map.hasOwnProperty(key)) continue;
            let   node = "#select-sheet".concat(i);
            let  select = "#tab".concat(i);
            let  input   ="#input-sheet".concat(i);
            $(select).text(key);
            $(select).addClass("hasSheet");
            let  options = '';

            for (let i = 0; i < map[key].length; i++) {
                options += "<option class='' value='" + map[key][i] + "'>" + map[key][i] + "</option>";

            }
            $(node).append(options);
            $(input).val(setFileName(key));
            i++;
        }

    }

    function setFileName(sheetName){
        if(sheetName.indexOf("sheet")!==-1){

             return filename.replace(".xlsx","");
        }
        else {
            return filename.replace(".xlsx","").concat(sheetName)
        }
    }



})




//写入input
function showTitles(map) {
    let i = 1;
    for( let key in map) {
      let   node = "#select-sheet".concat(i);
      let  select = '#tab'.concat(i);
      $(select).val(key);
      $(select).addClass("hasSheet");
      let  options = '';

        for (let i = 0; i < map[key].length; i++) {
            options += "<option class='' value='" + map[key][i] + "'>" + map[key][i] + "</option>";

        }
        $(node).append(options);
        i++;
    }

}

//下载ZIP包
function downloadZip(i){
    node = "input-sheet".concat(i);
    select ="#select-sheet".concat(i);
    tab    ="#tab".concat(i);
    download = "#download-".concat(i)
    title =document.getElementById(node).value;
    option = $(select).val();
    sheetName  = $(tab).text();

    $.ajax({
        url:'/upload/getTitle',
        type:'POST',
        data:{
            title:title,
            option:option,
            sheetName:sheetName

        },

        success:function (res) {
            alert("拆分完成")
            $(download).css("display","block");

        },
        error:function (res) {
            alert("发生错误")




        }
    })



}