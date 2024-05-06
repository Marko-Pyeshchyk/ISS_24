/* Generated by AN DISI Unibo */ 
package it.unibo.bw24core

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
import main.resources.robotvirtual.VrobotLLMoves24

class Bw24core ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 val vr = VrobotLLMoves24.create("localhost",myself)
		 var RSTEP = false; var N=0  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outblue("$name STARTS")
						 vr.halt()  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="doboundary", cond=doswitch() )
				}	 
				state("doboundary") { //this:State
					action { //it:State
						delay(200) 
						 RSTEP = vr.step(350)  
						if(  RSTEP  
						 ){forward("stepdone", "stepdone(1)" ,name ) 
						}
						else
						 {forward("stepfailed", "stepfailed(1)" ,name ) 
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="handleSonardata",cond=whenEvent("sonardata"))
					transition(edgeName="t01",targetState="doboundary",cond=whenDispatch("stepdone"))
					transition(edgeName="t02",targetState="turnAndgo",cond=whenDispatch("stepfailed"))
					transition(edgeName="t03",targetState="handleWolf",cond=whenEvent("wolf"))
				}	 
				state("handleWolf") { //this:State
					action { //it:State
						CommUtils.outmagenta("$name handleWolf")
						delay(1000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t04",targetState="handleSonardata",cond=whenEvent("sonardata"))
					transition(edgeName="t05",targetState="doboundary",cond=whenDispatch("stepdone"))
					transition(edgeName="t06",targetState="turnAndgo",cond=whenDispatch("stepfailed"))
				}	 
				state("turnAndgo") { //this:State
					action { //it:State
						 N = N + 1  
						 vr.turnLeft()  
						if(  N == 4  
						 ){ System.exit(0)  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="doboundary", cond=doswitch() )
				}	 
				state("handleSonardata") { //this:State
					action { //it:State
						CommUtils.outmagenta("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						delay(200) 
						 RSTEP = vr.step(350)  
						delay(2000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t07",targetState="doboundary",cond=whenDispatch("stepdone"))
					transition(edgeName="t08",targetState="turnAndgo",cond=whenDispatch("stepfailed"))
				}	 
			}
		}
} 
