package ru.skillbranch.devintensive.extensions

fun String.truncate(size:Int = 16):String{
    return if(this.trim().length > size){
        this.substring(0,size).trim() + "..."
    } else{
        this.trim()
    }
}

fun String.stripHtml():String{
    return this.trim().replace("\\s+".toRegex()," ").replace("<(\"[^\"]*\"|'[^']*'|[^'\">])*>".toRegex(),"")
}