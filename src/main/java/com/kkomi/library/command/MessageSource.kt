package com.kkomi.library.command

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.ArrayList
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 원리 먼저 입력된 메소드에서 pattern과 matcher를 사용하여 파라미터 공간을 찾아낸다. 해당 위치의 값을 List에 담는다.
 *
 * @author LittleK
 */
class MessageSource
/**
 * 메시지 소스를 생성하는 생성자 메소드
 *
 * @param message
 */
(var message: String // 메시지
) {

    var prefix: String? = null
        private set(prefix) {
            field = prefix
            this.toString = prefix + this.message!!
        } // 접두사
    val macro: Array<String> // 파라미터 자동 입력
    var toString: String //

    init {

        val pattern = Pattern.compile("<(.*?)>")
        val matcher = pattern.matcher(message)
        var macro: ArrayList<String>? = null

        while (matcher.find()) {
            if (macro == null)
                macro = ArrayList(2)

            macro.add(matcher.group())
        }

        this.macro = macro!!.toTypedArray()
        this.toString = message
    }

    fun toString(vararg args: String): String? {
        if (this.macro == null)
            return this.toString

        val builder = StringBuilder(this.toString!!)

        var i = 0
        val length = Math.min(this.macro.size, args.size)
        while (i < length) {
            val macro = this.macro[i]
            val index = builder.indexOf(macro)

            if (index >= 0)
                builder.replace(index, index + macro.length, args[i])
            i++
        }

        return builder.toString()
    }

    override fun toString(): String {
        return this.toString
    }

    companion object {

        /**
         * 접두사를 설정하는 메소드
         *
         * @param clazz
         * @param prefix
         */
        fun setPrefix(clazz: Class<*>, prefix: String?) {
            if (prefix == null)
                throw NullPointerException("prefix cannot be null")

            for (field in getMessageSourceFields(clazz)) {
                try {
                    (field.get(clazz) as MessageSource).prefix = prefix
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        }

        /**
         *
         * @param clazz
         * @return
         */
        private fun getMessageSourceFields(clazz: Class<*>): List<Field> {
            // 해당 클래스의 필드변수를 모두 가져옴 ( 전역변수 )
            val declaredFields = clazz.declaredFields
            // 해당 필드만큼의 ArrayList를 생성
            val fields = ArrayList<Field>(declaredFields.size)

            // 필드만큼 for문을 돌은
            for (field in declaredFields) {
                // 만약 필드의 클래스가 해당 클래스랑 같고 해당 필드가 스태틱이라면 값에게 접근 할 수 있게 해주고 필드목록에 추가시킨다.
                if (field.type == MessageSource::class.java && Modifier.isStatic(field.modifiers)) {
                    field.isAccessible = true
                    fields.add(field)
                }
            }

            return fields
        }
    }

}
