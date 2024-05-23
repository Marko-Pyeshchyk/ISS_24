/* Generated by AN DISI Unibo */ 
package it.unibo.sonardevice

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Sonardevice ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 lateinit var process  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outblue("sonardevice START")
						 process = Runtime.getRuntime().exec("python ./sonar.py")  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="s1", cond=doswitch() )
				}	 
				state("s1") { //this:State
					action { //it:State
						 var out = process.getInputStream().bufferedReader()  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="readOut", cond=doswitch() )
				}	 
				state("readOut") { //this:State
					action { //it:State
						 
									var D 
									var Distance = 0
									
									try {
										D = out.readline() 	
										val vd = D.toFloat()
										val v = vd.toInt()
										if (v <=  100) {
											Distance = v
										} else {
											Distance = 0
										}
										
									} catch (e: Exception) {
										println("Errore")
									}	
						if(  Distance > 0  
						 ){CommUtils.outblue("distanza = $Distance")
						emitLocalStreamEvent("sonardata", "distance($Distance)" ) 
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="s1", cond=doswitch() )
				}	 
			}
		}
} 
