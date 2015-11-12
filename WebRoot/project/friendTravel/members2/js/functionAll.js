function SetWinHeight(obj)
{	
    var iframeMain=obj;
    if (document.getElementById)
    {
        if (iframeMain && !window.opera)
        {
            if (iframeMain.contentDocument && iframeMain.contentDocument.body.offsetHeight) 
               iframeMain.height = iframeMain.contentDocument.body.offsetHeight+50; 
            else if(iframeMain.Document && iframeMain.Document.body.scrollHeight)
               iframeMain.height = iframeMain.Document.body.scrollHeight+50;
        }
    }
}