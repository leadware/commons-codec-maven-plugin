/**
 * 
 */
package net.leadware.commons.codec.plugin.base64;

/*
 * #%L
 * commons-codec-maven-plugin Mojo
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2013 - 2014 Leadware
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.apache.commons.codec.binary.Base64;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;


/**
 * Classe representant le MOJO d'encodage/decodage base 64
 * @author <a href="mailto:jetune@leadware.net">Jean-Jacques ETUNE NGI</a>
 * @since 30 juil. 2014 - 21:55:47
 */
@Mojo(name="encode-base64",
	  defaultPhase = LifecyclePhase.VALIDATE, 
	  threadSafe = true, 
	  requiresProject = true, 
	  requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class Base64Mojo extends AbstractMojo {

	/**
	 * Projet Maven en cours
	 */
	@Component
	private MavenProject project;
	
	/**
	 * Valeur de la chaine a encoder
	 */
	@Parameter(required = true)
	private String inputValue;
	
	/**
	 * nom de la variable resultat
	 */
	@Parameter(defaultValue = "output-property")
	private String outputProperty;
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		// Encodage
		String encoded = new String(Base64.encodeBase64(inputValue.getBytes()));
		
		// Affichage
		getLog().info("encoded identity ==> " + encoded);
		
		// Positionnement dans la variable resultat
		project.getProperties().setProperty(outputProperty, encoded);
	}

}
