package com.gemini.cerego.model

data class Response(
   val sets_count : Int,
   val sets : List<Vocab>
)