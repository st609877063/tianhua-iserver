/*  
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.  
For licensing, see LICENSE.html or http://ckeditor.com/license  
*/  
  
CKEDITOR.editorConfig = function( config )   
{   
    config.language = 'zh-cn';  

    config.filebrowserUploadUrl = 'ckeditor/uploader/upload.jsp';  

    config.filebrowserImageUploadUrl = 'ckeditor/uploader/upload.jsp?type=images';  

    config.filebrowserFlashUploadUrl = 'ckeditor/uploader/upload.jsp?type=flashs';  
    
    config.filebrowserImageBrowseUrl = 'ckeditor/uploader/browse.jsp?type=images';  

    config.filebrowserWindowWidth = '640';  

    config.filebrowserWindowHeight = '480';  
 
    //config.skin = 'v2';   
	// 背景颜色   
    config.uiColor = '#d4e4f6';   
     //config.toolbar_Full = [   
     //  ['Source','-','Save','NewPage','Preview','-','Templates'],   
     //  ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],   
     //  ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],      
     //  ['Image']
     //];   
        config.toolbar_Full = [   
       ['Source','-','Save','NewPage','Preview','-','Templates'],   
       ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],   
       ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],   
        //['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],   
       '/',   
       ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],   
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],   
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],   
        ['Link','Unlink','Anchor'],   
        ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],   
        '/',           ['Styles','Format','Font','FontSize'],   
        ['TextColor','BGColor']   
    ];   
    };  